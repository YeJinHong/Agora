import { registerVote, getVote } from '/api/vote';

// [voteStore 데이터 설정 실시]
const voteStore = {
    state: { // [변수들의 집합]
    },
    getters: { // [state의 변수들을 get 호출]

    },
    mutations: { // [변수들을 조작하는 함수들]

    },
    actions: { // [비동기 처리를 하는 함수들]
        // 청중 평가 생성 요청
        async sendVote(){
            console.log('투표 생성 요청');
            await registerVote(
                {},
                ({data}) => {
                    alert(`투표 생성 요청 성공`);
                    console.log(data);
                },
                (error) => {
                    alert(`투표 생성 요청 실패`);
                    console.log(error);
                }
            );
        },
        async getVote(debateId){
            console.log(`특정 토론 투표 결과 취합 조회`);
            await getVote(
                debateId,
                ({data}) => {
                    alert(`투표 조회 요청 성공`);
                    console.log(data);
                },
                (error) => {
                    alert(`투표 조회 요청 실패`);
                    console.log(error);
                }
            );
        }
    },
};

export default voteStore;