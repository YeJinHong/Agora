import { requestLogin, requestSignup } from "../common/api/accountAPI";
import createPersistedState from "vuex-persistedstate";

const state = {
  token: null,
  isLogin : false,

};

const getters = {
  getToken: state => {
    return state.token;
  }
};

const mutations = {
  setIsLogin:(state, isLogin) =>{
    state.isLogin = isLogin;
  },
  setToken: (state, token) => {
    state.token = token;
  }
};

const actions = {
  loginAction: async ({ commit }, loginData) => {
    const response = await requestLogin(loginData);

    if(response.data.accessToken != null){
      commit("setIsLogin", true);
      const accessToken = response.data.accessToken;
      commit("setToken", accessToken);
    }
  }
};



export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: window.sessionStorage,
      paths : ['accountStore'],
    }),

  ],
};
