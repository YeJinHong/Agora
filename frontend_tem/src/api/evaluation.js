import { apiInstance } from "./index.js";

const api = apiInstance();
 
async function registerEvaluation(data, success, fail) {
    // api.defaults.headers["Authorization"] = "Bearer" + sessionStorage.getItem("access-token");
    api.defaults.headers["Authorization"] = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWJhdGVAbmF2ZXIuY29tIiwiaXNzIjoiUmVzZXRDb250ZW50IiwiZXhwIjoxNjc1MzE5NTc4LCJpYXQiOjE2NzUzMTc3Nzh9.X1aQUw2go7cp6McR2j7LGebqBksXxwSYU4qOT4Tar5eVVcqHf7M-Qut7SrtoCMSzQlhjE31Y7bFljvhAbBzymg";
    console.log(data);
    const data2 = {
        debate_id : "2",
        evaluated_id : "kim@naver.com",
        content : [
            {parentId : 1, id : 4, point : 1},
            {parentId : 1, id : 5, point : 1},
            {parentId : 2, id : 6, point : 1},
            {parentId : 3, id : 7, point : 1},
            {parentId : 3, id : 8, point : 1} 
        ]
    };
    console.log('data2 : ')
    console.log(data2);
    await api.post(`/evaluations`, JSON.stringify(data2)).then(success).catch(fail);
}

async function getEvaluations(success, fail){
    // api.defaults.headers["Authorization"] = "Bearer" + sessionStorage.getItem("access-token");
    api.defaults.headers["Authorization"] = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWJhdGVAbmF2ZXIuY29tIiwiaXNzIjoiUmVzZXRDb250ZW50IiwiZXhwIjoxNjc1MzE5NTc4LCJpYXQiOjE2NzUzMTc3Nzh9.X1aQUw2go7cp6McR2j7LGebqBksXxwSYU4qOT4Tar5eVVcqHf7M-Qut7SrtoCMSzQlhjE31Y7bFljvhAbBzymg";
    await api.get(`/evaluations`).then(success).catch(fail);
}

export { registerEvaluation , getEvaluations };