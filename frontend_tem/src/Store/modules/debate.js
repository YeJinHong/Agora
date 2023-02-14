const state = {
    participant: {},
    my_name: null,
    participant_list: false,
    micro_phone: true,
    video: true,
    chat_box: false,
    document_box: false,
    middle_box: true,
    chatList: [],
    chatSocket: null,
    stompClient: null,
    webRtcSocket: null,
};

const getters = {
    isDocumentBox: () => {
        return state.document_box;
    },
    getWebRtcSocket: () => {
        return state.webRtcSocket;
    },
    getParticipant: () => {
        return state.participant;
    },
};

const mutations = {
    Register(state, name) {
        state.my_name = name
    },
    participantRegister(state, participant) {
        console.log('실행실행')
        state.participant = participant
    },
    participantList(state) {
        if (state.chat_box === true) {
            state.chat_box = false
            state.participant_list = !state.participant_list
        } else {
            state.participant_list = !state.participant_list
        }
    },
    microPhone(state) {
        state.micro_phone = !state.micro_phone
    },
    chatBox(state) {
        if (state.participant_list === true) {
            state.participant_list = false
            state.chat_box = !state.chat_box
        } else {
            state.chat_box = !state.chat_box
        }
    },
    documentBox(state) {
        state.document_box = !state.document_box
    },
    audioControl(state) {
        console.log('뮤테이션')
        console.log(state.participant[state.my_name].rtcPeer)
        state.participant[state.my_name].rtcPeer.audioEnabled = !state.participant[state.my_name].rtcPeer.audioEnabled
        state.micro_phone = !state.micro_phone
    },
    videoControl(state) {
        console.log('비디오우')
        state.participant[state.my_name].rtcPeer.videoEnabled = !state.participant[state.my_name].rtcPeer.videoEnabled
        state.video = !state.video

    }
};

const actions = {
    getAudioControl: function (context) {
        console.log('액션')
        return context.commit('audioControl');
    },
    getVideoControl: function (context) {
        console.log('액션')
        return context.commit('videoControl');
    }

}


export default {
    namespaced: true,
    state,
    getters,
    mutations,
    actions
};