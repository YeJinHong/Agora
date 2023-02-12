<template>
  <div>
    <div id="room">
      <h2 id="room-header"></h2>
      <div id="participants">
        <div id="participants-agree"></div>
        <div id="moderator"></div>
        <div id="participants-opp" style="float: right;"></div>
      </div>
      <div id="timer" style="font-size: 20px"></div>
      <div id="screen"></div>
      <div id="buttons" style="display: none">
        <input type="button" id="button-start" onmouseup="start();"
               value="start">
        <input type="button" id="button-stop" onmouseup="stop();"
               value="stop">
        <input type="button" id="button-share-on" onmouseup="shareScreen()"
               value="share screen on">
        <input type="button" id="button-share-off" onmouseup="stopShareScreen()" style="display: none"
               value="share screen off">
        <input type="button" id="vidOn" onmouseup="videoOnOff()" style="display: none"
               value="video On">
        <input type="button" id="vidOff" onmouseup="videoOnOff()"
               value="video Off">
        <input type="button" id="audOn" onmouseup="audioOnOff()" style="display: none"
               value="Audio On">
        <input type="button" id="audOff" onmouseup="audioOnOff()"
               value="Audio Off">
        <input type="button" id="button-system-comment" onmouseup="sendSystemComment()"
               value="system comment">
        <input type="button" id="button-leave" onmouseup="leaveRoom();"
               value="Leave room">
      </div>
    </div>
  </div>
</template>

<script>
import {mapState, useStore} from "vuex";
import kurentoUtils from "kurento-utils";
import {reactive} from "vue";
import Participant from "../../../assets/js/participant.js";

export default {
  name: 'debate2',
  components: {},
  setup() {
    const store = useStore();
    const data = reactive({
      ws: store.getters["debate/getWebRtcSocket"]
    })
    return {store, data}
  },
  data() {
    return {
      participants: {},
      mame: '',
      title: '',
      position: ''
    }
  },
  computed: {
    ...mapState('debate', {middle_box: 'middle_box'})
  },
  mounted() {
    this.connect()
  },
  methods: {
    makeWebsocket() {
      this.data.ws = new WebSocket('wss://localhost:8443/groupcall');
      this.data.ws.onopen = () => {
        console.log('WebSocket connection established');
        this.register()
      };

      this.data.ws.onerror = (error) => {
        console.error('WebSocket connection error:', error);
      };
    },
    async connect() {
      await this.makeWebsocket()

      this.data.ws.onmessage = (message) => {
        let parsedMessage = JSON.parse(message.data);
        console.info('Received message: ' + message.data);

        switch (parsedMessage.id) {
          case 'existingParticipants':
            this.onExistingParticipants(parsedMessage)
            break;
          case 'newParticipantArrived':
            console.log('newArrived')
            onNewParticipant(parsedMessage);
            break;
          case 'participantLeft':
            onParticipantLeft(parsedMessage);
            break;
          case 'receiveVideoAnswer':
            console.log(this.participants)
            receiveVideoResponse(parsedMessage);
            break;
          case 'iceCandidate':
            console.log('iceCandidate', parsedMessage)
            this.participants[parsedMessage.name].rtcPeer.addIceCandidate(parsedMessage.candidate, function (error) {
              if (error) {
                console.error("Error adding candidate: " + error);
                return;
              }
            });
            break;
          case 'timeRemaining':
            var time = parsedMessage.time;
            document.getElementById('timer').innerText = parseInt(time / 60) + ':' + time % 60
            break
          case 'pauseSpeaking':
            var time = parsedMessage.time;
            document.getElementById('timer').innerText = parseInt(time / 60) + ':' + time % 60
            break
          case 'receiveSystemComment':
            alert(parsedMessage.comment)
            break;
          case 'terminateDebate':
            alert('토론이 종료되었습니다.');
            this.leaveRoom();
            break
          default:
            console.error('Unrecognized message', parsedMessage);
        }
      }

      console.log('connect done')
    },
    register() {
      console.log('register')
      this.name = '김피먹';
      this.title = '아고라 완성 가능??';
      let debateId = 1;
      this.position = '찬성'
      let roomType = '시간총량제';
      let time = 100;

      this.sendMessage({
        id: 'createRoom',
        debateId: debateId,
        title: this.title,
        roomType: roomType,
        time: time
      })

      let message = {
        id: 'joinRoom',
        userName: this.name,
        debateId: debateId,
        title: this.title,
        position: this.position,
      }
      this.sendMessage(message);
    },

    onNewParticipant(request) {
      this.receiveVideo(request.name, request.position, request.isScreen);
    },

    receiveVideoResponse(result) {
      this.participants[result.name].rtcPeer.processAnswer(result.sdpAnswer, function (error) {
        if (error) return console.error(error);
      });
    },

    callResponse(message) {
      if (message.response !== 'accepted') {
        console.info('Call not accepted by peer. Closing call');
        stop();
      } else {
        webRtcPeer.processAnswer(message.sdpAnswer, function (error) {
          if (error) return console.error(error);
        });
      }
    },

    onExistingParticipants(msg) {
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

      if (false) {
        console.log('share screen:', this.name, this.position)
        let participant = new Participant(this.name, this.position, true);
        this.participants[this.name] = participant;
        let video = participant.getVideoElement();

        if (navigator.getDisplayMedia || navigator.mediaDevices.getDisplayMedia) {
          if (navigator.mediaDevices.getDisplayMedia) {
            navigator.mediaDevices
                .getDisplayMedia({video: true, audio: true})
                .then((stream) => {
                  video.srcObject = stream;
                  options = {
                    videoStream: stream,
                    mediaConstraints: constraints,
                    sendSource: "screen",
                    onicecandidate: participant.onIceCandidate.bind(participant),
                  };
                  participant.rtcPeer =
                      new kurentoUtils.WebRtcPeer.WebRtcPeerSendrecv(options,
                          function (error) {
                            if (error) {
                              return console.error(error);
                            }
                            this.generateOffer(participant.offerToReceiveVideo.bind(participant));
                          });
                  msg.data.forEach(m => {
                    this.receiveVideo(m.name, m.position)
                  });
                });
          }
        }

      } else {
        console.log(this.name + " registered in room " + this.title);
        let participant = new Participant(this.name, this.position, msg.isScreen);
        this.participants[name] = participant;
        let video = participant.getVideoElement();

        let options = {
          localVideo: video,
          mediaConstraints: constraints,
          onicecandidate: participant.onIceCandidate.bind(participant)
        }

        if (this.position === '청중') {
          participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options,
              function (error) {
                if (error) {
                  return console.error(error);
                }
                this.generateOffer(participant.offerToReceiveVideo.bind(participant));
              });
        } else {
          participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,
              function (error) {
                if (error) {
                  return console.error(error);
                }
                this.generateOffer(participant.offerToReceiveVideo.bind(participant));
              });
        }

        msg.data.forEach(m => (this.receiveVideo(m.name, m.position, m.isScreen)));
      }

    },

    start() {
      let debateId = document.getElementById('debateId').value;

      this.sendMessage({
        id: 'startSpeaking',
        debateId: debateId
      });
    },

    stop() {
      let debateId = document.getElementById('debateId').value;

      this.sendMessage({
        id: 'pauseSpeaking',
        debateId: debateId
      });
    },

    shareScreen() {
      name = document.getElementById('name').value;
      let debateId = document.getElementById('debateId').value;
      let title = document.getElementById('title').value;
      isScreen = true;

      this.sendMessage({
        id: 'leaveRoom'
      });
      for (var key in participants) {
        if (participants[key].name !== name) {
          var partVideo = document.getElementById("video-" + participants[key].name);
          console.log('leave', participants[key].name);
          partVideo.parentElement.remove();
        }
      }
      delete this.participants[name];
      document.getElementById("video-" + name).parentElement.remove();

      var message = {
        id: 'shareScreen',
        userName: name,
        debateId: debateId,
        title: title,
        position: position,
      }
      // name = 'screen_' + name;
      this.sendMessage(message);
      document.getElementById("button-share-on").style.display = "none";
      document.getElementById("button-share-off").style.display = "";
    },

    stopShareScreen() {
      name = document.getElementById('name').value;
      let debateId = document.getElementById('debateId').value;
      let title = document.getElementById('title').value;
      isScreen = false;

      this.sendMessage({
        id: 'leaveRoom'
      });
      for (let key in participants) {
        if (participants[key].name !== name) {
          let partVideo = document.getElementById("video-" + participants[key].name);
          console.log('leave', participants[key].name);
          partVideo.parentElement.remove();
        }
      }
      delete participants[name];
      document.getElementById(name).remove();
      let video = document.getElementById("video-" + name);
      video.srcObject.getTracks().forEach(track => track.stop());
      video.remove();


      var message = {
        id: 'joinRoom',
        userName: name,
        debateId: debateId,
        title: title,
        position: position,
      }
      // name = 'screen_' + name;
      this.sendMessage(message);
      document.getElementById("button-share-on").style.display = "";
      document.getElementById("button-share-off").style.display = "none";
    },

    leaveRoom() {
      sendMessage({
        id: 'leaveRoom'
      });

      for (var key in participants) {
        participants[key].dispose();
      }

      document.getElementById('join').style.display = 'block';
      document.getElementById('room').style.display = 'none';

      ws.close();
    },

    receiveVideo(name, position, isScreen) {
      console.log(name, position)
      var sender = name;
      var participant = new Participant(sender, position, isScreen);
      participants[sender] = participant;
      var video = participant.getVideoElement();

      var options = {
        remoteVideo: video,
        onicecandidate: participant.onIceCandidate.bind(participant)
      }

      participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options,
          function (error) {
            if (error) {
              return console.error(error);
            }
            this.generateOffer(participant.offerToReceiveVideo.bind(participant));
          });
    },

    onParticipantLeft(request) {
      console.log('Participant ' + request.name + ' left');
      var participant = participants[request.name];
      participant.dispose();
      document.getElementById('video-' + request.name).remove();
      delete participants[request.name];
    },

    sendMessage(message) {
      console.log(this.data.ws)
      console.log('send message', message)
      let jsonMessage = JSON.stringify(message);
      console.log('Sending message: ' + jsonMessage);
      this.data.ws.send(jsonMessage);
      console.log(this.data.ws)
    },

    videoOnOff() {
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
    },

    audioOnOff() {
      if (participants[name].rtcPeer.audioEnabled) {
        participants[name].rtcPeer.audioEnabled = false;
        document.getElementById("audOn").style.display = "";
        document.getElementById("audOff").style.display = "none";
      } else {
        participants[name].rtcPeer.audioEnabled = true;
        document.getElementById("audOn").style.display = "none";
        document.getElementById("audOff").style.display = "";
      }
    },

    sendSystemComment() {
      let debateId = document.getElementById('debateId').value;

      sendMessage({
        id: 'sendSystemComment',
        debateId: debateId,
        comment: 'test',
      })
    },

    terminateDebate() {
      let debateId = document.getElementById('debateId').value;

      sendMessage({
        id: 'terminateDebate',
        debateId: debateId
      })
    }
  }
}

</script>

<style scoped>

</style>