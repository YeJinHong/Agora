import { apiInstance } from "./index.js";

const api = apiInstance();

function login(user, success, fail) {
    api.post(`/api/v1/auth/login`, user).then(success).catch(fail);
}

function findById(userid, success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    api.get(`/me`).then(success).catch(fail);
}

function tokenRegeneration(user, success, fail) {
    api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
    api.post(`/user/refresh`, user).then(success).catch(fail);
}

function logout(userid, success, fail) {
    api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

const requestSignup = payload => api.post("/users", payload)

export { login, findById, tokenRegeneration, logout, requestSignup };
