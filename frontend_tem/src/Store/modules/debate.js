const state = {
    participant: 0,
    participant_list: false,
    micro_phone: true,
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
    }
};

const mutations = {
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
    }
};


export default {
    namespaced: true,
    state,
    getters,
    mutations
};