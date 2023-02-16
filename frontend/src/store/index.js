import { createStore } from "vuex";
import accountStore from "./accountStore";
import menuStore from "./menuStore";
import platformInfoStore from "./platformInfoStore";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  modules: {
    accountStore : accountStore,
    menuStore,
    platformInfoStore
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: window.sessionStorage,
      paths : ['accountStore'],
    }),

  ],

});
