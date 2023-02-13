import {searchAll, getCategoryList} from "../../api/Debate";

const debate = {
    namespaced: true,
    state: {
        debateId : 15,
        keyword :"",
        condition: "",
        selectedOptionName : "",
        selectedCategoryIdList : "",
        debateList: [],
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
        categoryList : [],
    },
    getters: {
        getDebateId : function (state) {
            return state.debateId;
        },
        isDocumentBox: function (state) {
            return state.document_box;
        },
        getDebateList: function (state) {
            return state.debateList;
        },
        getKeyword : function (state){
            return state.keyword;
        },
        getCategoryList : function(state){
            return state.categoryList;
        },
        getSelectedCategoryList : function(state){
            return state.selectedCategoryIdList;
        },
    },
    mutations: {
        SET_DEBATE_ID : (state, debateId) => {
            state.debateId = debateId;
        },
        SET_DEBATE_LIST: (state, debateList) => {
            state.debateList = [];
            debateList.forEach((debate) => {
                state.debateList.push(debate);
            });
        },
        SET_CATEGORY_LIST : (state, categoryList) => {
            state.categoryList = categoryList;
        },
        SET_SELECTED_CATEGORY_LIST : (state, categoryList) => {
            state.selectedCategoryIdList = categoryList;
        }
    },
    actions: {
        async searchDebateList({state, commit}, condition) {
            condition.categoryList = state.selectedCategoryIdList;
            console.log(state.selectedCategoryIdList);
            console.log(condition.categoryList);
            await searchAll(condition, ({data}) => {
                if (data.message === "Success") {
                    console.log(data.data.content);
                    commit("SET_DEBATE_LIST", data.data.content);
                }
            }, (error) => {
                console.log("문제가 발생하였습니다.")
            })
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
        setSelectedCategoryList({state, commit}, categoryList){
            state.selectedCategoryIdList = "";
            categoryList.forEach((category) => {
                state.selectedCategoryIdList = state.selectedCategoryIdList + category + ', ';
            });
            console.log(state.selectedCategoryIdList);
            commit("SET_SELECTED_CATEGORY_LIST", categoryList);
        },
        async getCategoryList({state, commit}){
            await getCategoryList(
                ({data}) => {
                    console.log('카테고리 정보 받아오기!!!!!!!!!!!');
                    console.log(data);
                    console.log(data.data);

                    commit("SET_CATEGORY_LIST", data.data);
                },
                (error) => {
                    console.log('카테고리 정보 조회중 에러 발생');
                }
            )
        },
        documentBox(state) {
            state.document_box = !state.document_box
        }
    }
}

export default debate;
