import { apiInstance } from "./index.js";

const api = apiInstance();

async function registerVote(data, success, fail) {
    // api.defaults.headers["Authorization"] = "Bearer" + sessionStorage.getItem("access-token");
    api.defaults.headers["Authorization"] = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWJhdGVAbmF2ZXIuY29tIiwiaXNzIjoiUmVzZXRDb250ZW50IiwiZXhwIjoxNjc1MzI1NTY3LCJpYXQiOjE2NzUzMjM3Njd9.9rf5RdquRsPCL2glp51r9zidW7t0wDXRtHNjNYQoefXWeOQ5zSJE4UplOJUhT9SOo72u16NetlzFV6ZnpisUCg";
    // data가 Proxy 형태로 바뀌어 넘어오면서 정상적인 호출이 어려움.
    // 임시로 파라미터값을 하드코딩하여 기능을 테스트함.
    const data2 = {
        debate_id : "2",
        mvp_id : "kim@naver.com",
        perspective_id : "1",
    };
    await api.post(`/vote`, JSON.stringify(data2)).then(success).catch(fail);
}

async function getVote(debateId, success, fail){
    debateId = 2;
    await api.get(`/vote/debates/${debateId}`).then(success).catch(fail);
}


export { registerVote, getVote };