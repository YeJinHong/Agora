let participants = {};
let userName;
let roomName;
let roomId;
let position;
let ws = new WebSocket('wss://' + location.host + '/groupcall');

ws.onmessage = function (message) {
    let parsedMessage = JSON.parse(message.data);
    console.log("Received message: " + message.data);

    switch (parsedMessage.id) {
        case "existingParticipants":
            onExistingParticipants(parsedMessage);
            break;
        case "newParticipantArrived":
            onNewParticipant(parsedMessage);
            break;
        case "participantLeft":
            onParticipantLeft(parsedMessage);
            break;
        case "receiveVideoAnswer":
            receiveVideoResponse(parsedMessage);
            break;
        case "iceCandidate":
            participants[parsedMessage.name].rtcPeer.addIceCandidate(
                parsedMessage.candidate,
                function (error) {
                    if (error) {
                        console.error("Error adding candidate: " + error);
                        return;
                    }
                }
            );
            break;
        case 'timeRemaining':
            var time = parsedMessage.time;
            document.getElementById('timer').innerText = parseInt(time / 60) + ':' + time % 60
            break;
        case 'pauseSpeaking':
            var time = parsedMessage.time;
            document.getElementById('timer').innerText = parseInt(time / 60) + ':' + time % 60
            break;
        default:
            console.error("Unrecognized message", parsedMessage);
    }
};

function register() {
    userName = document.getElementById('name').value;
    roomName = document.getElementById('roomName').value;
    roomId = roomName;
    position = document.getElementById('position').value;

    document.getElementById('room-header').innerText = 'ROOM ' + room;
    document.getElementById('join').style.display = 'none';

    document.getElementById('room').style.display = 'block';
    // DebateRoom(userName, roomName, roomId, position);

    const message = {
        id: "joinRoom",
        userName: userName,
        roomName: roomName,
        debateId: roomId,
        position: position,
    };
    sendMessage(message);
}

// 새로운 사용자가 들어온 경우 비디오 처리
function onNewParticipant(request) {
    receiveVideo(request.name, request.position);
}

// 새로운 사용자의 비디오 연결
function receiveVideo(sender, position) {
    let participant = new Participant(sender, position);
    participants[sender] = participant;

    let video = participant.getVideoElement();
    console.log(video);

    let options = {
        remoteVideo: video,
        onIceCandidate: participant.onIceCandidate.bind(participant)
    };

    participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(
        options,
        function (error) {
            if (error) {
                return console.error(error);
            }
            this.generateOffer(participant.offerToReceiveVideo.bind(participant));
        }
    );
}

class Participant {
    constructor(name, position) {
        this.name = name;
        this.position = position;
        let rtcPeer;

        let video = document.createElement("video");
        video.id = "video-" + name;
        video.autoplay = true;
        video.controls = false;

        if (name.includes("screen")) {
            document.getElementById('screen').append(video);
        } else if (position === '찬성') {
            document.getElementById('participants').append(video);
        } else if (position === '반대') {
            document.getElementById('participants-opp').append(video);
        }

        this.getVideoElement = function () {
            return video;
        };

        this.offerToReceiveVideo = function (error, offerSdp, wp) {
            if (error) {
                return console.error("sdp offer error");
            }
            console.log("Invoking SDP offer callback function");

            let message = {
                id: "receiveVideoFrom",
                sender: name,
                sdpOffer: offerSdp
            }
            sendMessage(message);
        };

        this.onIceCandidate = function (candidate, wp) {
            console.log("Local candidate" + JSON.stringify(candidate));

            let message = {
                id: "onIceCandidate",
                candidate: candidate,
                name: name
            };
            sendMessage(message);
        };

        this.dispose = function () {
            console.log("Disposing participant " + this.name);
            this.rtcPeer.dispose();
        };
    }
}

function receiveVideoResponse(result) {
    // sdp negotiation을 완료하기 위해 호출. sdp 응답을 받으면 호출되는 콜백 함수
    participants[result.name].rtcPeer.processAnswer(
        result.sdpAnswer,
        function (error) {
            if (error) {
                return console.error(error);
            }
        }
    );
}

function onExistingParticipants(message) {
    let constraints = {
        audio: true,
        video: {
            mandatory: {
                maxWidth: 320,
                maxFrameRate: 15,
                minFrameRate: 15
            }
        }
    };

    if (userName.includes("screen")) {
        console.log("#####" + userName);

        let participant = new Participant(userName, position);
        participants[userName] = participant;

        let video = participant.getVideoElement();
        if (navigator.getDisplayMedia || navigator.mediaDevices.getDisplayMedia) {
            if (navigator.mediaDevices.getDisplayMedia) {
                navigator
                    .mediaDevices
                    .getDisplayMedia({video: true, audio: true})
                    .then((stream) => {
                        video.srcObject = stream;
                        options = {
                            videoStream: stream,
                            mediaConstraints: constraints,
                            sendSource: "screen",
                            onIceCandidate: participant.onIceCandidate.bind(participant)
                        };
                        participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(
                            options,
                            function (error) {
                                if (error) {
                                    return console.error(error);
                                }
                                this.generateOffer(
                                    participant.offerToReceiveVideo.bind(participant)
                                );
                            }
                        );
                        message.data.forEach(receiveVideo);
                    });
            }
        }
    } else {
        // 화면 공유가 아닌 경우
        console.log(userName + " registered in room " + roomName);

        let participant = new Participant(userName, position);
        participants[userName] = participant;

        let video = participant.getVideoElement();
        console.log(video);

        let options = {
            localVideo: video,
            mediaConstraints: constraints,
            onIceCandidate: participant.onIceCandidate.bind(participant)
        };

        participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(
            options,
            function (error) {
                if (error) {
                    return console.error(error);
                }
                this.generateOffer(participant.offerToReceiveVideo.bind(participant));
            }
        );
        message.data.forEach(receiveVideo);
    }
}

function onParticipantLeft(request) {
    console.log("Participant " + request.name + " left");
    document.getElementById("video-" + request.name).remove();

    let participant = participants[request.name];
    participant.dispose();
    delete participants[request.name];
}

function leaveRoom() {
    sendMessage({
        id: "leaveRoom"
    });
    for (let key in participants) {
        participants[key].dispose();
    }
    document.getElementById('room-header').innerText = '';
    document.getElementById('join').style.display = 'block';
    document.getElementById('room').style.display = 'none';
    ws.close();
}

function sendMessage(message) {
    let jsonMessage = JSON.stringify(message);
    console.log("Sending message: " + jsonMessage);
    ws.send(jsonMessage);
}

function videoOnOff() {
    if (participants[name].rtcPeer.videoEnabled) {
        // 끌때
        participants[name].rtcPeer.videoEnabled = false;
        document.getElementById("vidOn").style.display = "";
        document.getElementById("vidOff").style.display = "none";
    } else {
        participants[name].rtcPeer.videoEnabled = true;
        document.getElementById("vidOn").style.display = "none";
        document.getElementById("vidOff").style.display = "";
    }
}

function audioOnOff() {
    if (participants[name].rtcPeer.audioEnabled) {
        participants[name].rtcPeer.audioEnabled = false;
        document.getElementById("audOn").style.display = "";
        document.getElementById("audOff").style.display = "none";
    } else {
        participants[name].rtcPeer.audioEnabled = true;
        document.getElementById("audOn").style.display = "none";
        document.getElementById("audOff").style.display = "";
    }
}

function start() {
    var room = document.getElementById('roomName').value;

    sendMessage({
        id: 'startSpeaking',
        debateId: room
    });
}

function stop() {
    var room = document.getElementById('roomName').value;

    sendMessage({
        id: 'pauseSpeaking',
        debateId: room
    });
}

function shareScreen() {
    if (name === userName) {
        console.log("####shareScreen" + name);
        sendMessage({
            id: "leaveRoom",
        });
        for (var key in participants) {
            if (participants[key].name !== name) {
                var partVideo = document.getElementById(
                    "video-" + participants[key].name
                );
                console.log(participants[key].name);
                partVideo.parentElement.removeChild(partVideo);
            }
        }
        delete participants[name];
        document.getElementById("video-" + name).remove();

        const message = {
            id: "shareScreen",
            name: userName,
            room: roomId,
        };
        ws.send(JSON.stringify(message));
        name = "screen" + name;
    } else {
        sendMessage({
            id: "leaveRoom",
        });
        for (var key in participants) {
            participants[key].dispose();
            if (participants[key].name !== name) {
                var partVideo = document.getElementById(
                    "video-" + participants[key].name
                );
                console.log(participants[key].name);
                document.getElementById("participants").removeChild(partVideo);
            }
        }
        delete participants[name];
        document.getElementById("video-" + name).remove();
        const message = {
            id: "joinRoom",
            name: userName,
            room: roomId,
        };
        ws.send(JSON.stringify(message));
        name = userName;
    }
}

// }
