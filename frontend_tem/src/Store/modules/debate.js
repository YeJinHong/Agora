import {searchAll, getCategoryList} from "../../api/Debate";

const debate = {
    namespaced: true,
    state: {
        debateId : '',
        keyword :"",
        condition: "",
        selectedOptionName : "",
        selectedCategoryIdList : "",
        categoryList : [],
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
        selectedDebateId : "",

        // 페이징 처리용 데이터
        selectedPageIndex : 1,
        totalPages : 0, // 페이지로 제공되는 총 페이지 수
        totalElements : 0, // 모든 페이지에 존재하는 총 원소 수
        pageNumber : '', // 페이지 번호 (0번부터 시작)
        size : 0, // 한 페이지당 요청되는 크기
        numberOfElements : 0, // 현재 페이지에 보여지는 수.

    },
    getters: {
        getDebateId : () => {
            return state.debateId;
        },
        isDocumentBox: () => {
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
        getSelectedDebateId : function(state){
            return state.selectedDebateId;
        }
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
        },
        SET_SELECTED_DEBATE_ID : (state, debateId) => {
            state.selectedDebateId = debateId;
        },
        SET_TOTAL_PAGES : (state, totalPages) => {
            state.totalPages = totalPages;
        },
        SET_TOTAL_ELEMENTS : (state, totalElements) => {
            state.totalElements = totalElements;
        },
        SET_PAGE_NUMBER : (state, pageNumber) => {
            state.pageNumber = pageNumber;
        },
        SET_SIZE : (state, size) => {
            state.size = size;
        },
        SET_NUMBER_OF_ELEMENTS : (state, numberOfElements) => {
            state.numberOfElements = numberOfElements;
        },
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
                    commit("SET_TOTAL_PAGE", data.data.totalPages);
                    commit("SET_PAGE_NUMBER", data.data.pageable.pageNumber);
                    commit("SET_SIZE", data.data.size);
                    commit("SET_NUMBER_OF_ELEMENTS", data.data.numberOfElements);
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
        },
        setSelectedDebateId({commit}, debateId){
            commit("SET_SELECTED_DEBATE_ID", debateId);
        }
    }
}

export default debate;
