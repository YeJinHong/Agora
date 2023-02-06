import { registerEvaluation, getEvaluations } from '/api/evaluation';

// [evaluationStore 데이터 설정 실시]
const evaluationStore = {
    state: { // [변수들의 집합]
        userEvaluations : {},
    },
    getters: { // [state의 변수들을 get 호출]

    },
    mutations: { // [변수들을 조작하는 함수들]
        SET_USER_EVALUATIONS : (state, userEvaluations) => {
            state.userEvaluations = userEvaluations;
        }
    },
    actions: { // [비동기 처리를 하는 함수들]
        // 상호 평가 등록(생성) 요청
        async registerEval(evaluationData) {
            console.log(evaluationData.state);
            await registerEvaluation(
                evaluationData,
                ({data}) => {
                    if(data.message == "Success"){
                        alert('상호 평가 데이터 저장에 성공하였습니다.')
                    } else {
                        alert('상호 평가 데이터 저장 실패')
                    }
                },
                (error) => {
                    alert('상호 평가 데이터 저장 실패. 에러 발생. 입력값 확인');
                    console.log(error);
                }
            );
        },
        async getEval({commit}){
            console.log(`나의 상호 평가 데이터 조회 요청`);
            await getEvaluations(
                ({data}) => {
                    alert('상호 평가 데이터 조회 성공.')
                    console.log(data);
                    commit("SET_USER_EVALUATIONS", data);
                },
                (error) => {
                    alert('상호 평가 데이터 조회 실패. 에러 발생. 입력값 확인');
                    console.log(error);
                }
            );
        }
    },
};

export default evaluationStore;