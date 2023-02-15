import {searchAll, getCategoryList} from "../../api/Debate";

const state = {
    // 상세 정보 조회용
    debateId : '',
    debateInfo : {debateId : 3, title : "환승이별 vs 잠수이별"}, // TODO : 더미데이터 삭제

    // 토론 목록 검색용 1
    keyword :"",
    condition: "",
    selectedCategoryIdList : [],
    categoryList : [],
    debateList: [],

    // 토론 목록 검색용 2 - 페이징 처리
    totalPages : 0, // 페이지로 제공되는 총 페이지 수
    totalElements : 0, // 모든 페이지에 존재하는 총 원소 수
    pageNumber : '', // 페이지 번호 (0번부터 시작)
    numberOfElements : 0, // 현재 페이지에 보여지는 수.
    offset : 0, // 현재 페이지 시작 인덱스값.

    // 토론 참여용
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
    participantInfo: null,
};

const getters = {
    getDebateId : () => {
        return state.debateId;
    },
    isDocumentBox: () => {
        return state.document_box;
    },
    getWebRtcSocket: () => {
        return state.webRtcSocket;
    },
    getDebateList: function (state) {
        return state.debateList;
    },
    getPageNumber : function(state){
        return state.pageNumber;
    },
    getParticipant: () => {
        return state.participant;
    },
};

const mutations = {
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
    // 토론 검색기능 - 페이징 관련
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
    },
    SET_DEBATE_INFO : (state, debate) => {
        state.debateInfo = debate;
    },
    //토론메인창 UI
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
    //음성,영상제어
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
    },
    participantInfo(state, data) {
        state.participantInfo = data
    }
};

const actions = {
    //음성, 영상 제어
    getAudioControl: function (context) {
        console.log('액션')
        return context.commit('audioControl');
    },
    getVideoControl: function (context) {
        console.log('액션')
        return context.commit('videoControl');
    },

    // 토론 리스트 검색 API 요청
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
                console.log(data.data.content);
            }
        }, (error) => {
            console.log("토론 목록 조회 중 문제가 발생하였습니다.")
        })
    },
    // 토론 카테고리 검색 API 요청
    async getCategoryList({commit}){
        await getCategoryList(
            ({data}) => {
                commit("SET_CATEGORY_LIST", data.data);
                if(state.selectedCategoryIdList == [])
                    commit("SET_SELECTED_CATEGORY_LIST", data.data);
            },
            (error) => {
                console.log(error);
                console.log('카테고리 정보 조회중 에러 발생');
            }
        )
    },
}


export default {
    namespaced: true,
    state,
    getters,
    mutations,
    actions
};