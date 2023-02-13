import {searchAll, getCategoryList} from "../../api/Debate";

const debate = {
    namespaced: true,
    state: {
        debateId : '',
        keyword :"",
        condition: "",
        selectedCategoryIdList : [],
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
        totalPages : 0, // 페이지로 제공되는 총 페이지 수
        totalElements : 0, // 모든 페이지에 존재하는 총 원소 수
        pageNumber : '', // 페이지 번호 (0번부터 시작)
        numberOfElements : 0, // 현재 페이지에 보여지는 수.
        offset : 0,

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
        },
        getPageNumber : function(state){
            return state.pageNumber;
       }
    },
    mutations: {
        // 토론 리스트 관련
        SET_DEBATE_ID : (state, debateId) => {
            state.debateId = debateId;
        },
        SET_DEBATE_LIST: (state, debateList) => {
            state.debateList = [];
            debateList.forEach((debate) => {
                state.debateList.push(debate);
            });
        },
        // 토론 검색 기능 관련
        SET_KEYWORD : (state, keyword) => {
            state.keyword = keyword;
        },
        SET_CONDITION : (state, condition) => {
            state.condition = condition;
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
        SET_NUMBER_OF_ELEMENTS : (state, numberOfElements) => {
            state.numberOfElements = numberOfElements;
        },  
        SET_OFFSET : (state, offset) => {
            state.offset = offset;
        }
    },
    actions: {
        async searchDebateList({state, commit}, search) {
            search.condition = state.condition;
            search.keyword = state.keyword;
            if(search.page == undefined){
                search.page = 0;
            }
            search.categoryList = state.selectedCategoryIdList;
            await searchAll(search, ({data}) => {
                if (data.message === "Success") {
                    commit("SET_DEBATE_LIST", data.data.content);
                    commit("SET_TOTAL_PAGES", data.data.totalPages);
                    commit("SET_TOTAL_ELEMENTS", data.data.totalElements);
                    commit("SET_PAGE_NUMBER", data.data.pageable.pageNumber);
                    commit("SET_NUMBER_OF_ELEMENTS", data.data.numberOfElements);
                    commit("SET_OFFSET", data.data.pageable.offset);
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
            
            commit("SET_SELECTED_CATEGORY_LIST", categoryList);
        },
        async getCategoryList({state, commit}){
            await getCategoryList(
                ({data}) => {
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
