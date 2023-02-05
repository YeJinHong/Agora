<template>
  <!-- Header -->
  <header class="header header-page">
    <div class="header-fixed">
      <nav class="navbar navbar-expand-lg header-nav scroll-sticky">
        <div class="container">
          <navbar></navbar>
          <div class="main-menu-wrapper">
            <menuheader></menuheader>
            <mainnav></mainnav>
          </div>

          <signpages v-if="userInfo !== null && isLogin && isValidToken"></signpages>
          <headerpage v-else></headerpage>
        </div>
      </nav>
    </div>
  </header>
  <!-- /Header -->
</template>
<script>
import Vue, {computed} from 'vue'
import {useStore} from "vuex";

export default {
  name: 'loginheader',
  components: {},
  props: {},
  setup() {
    const store = useStore();
    const userInfo = computed(() => store.getters["userStore/checkUserInfo"]);
    const isLogin = computed(() => store.state.userStore.isLogin);
    const isValidToken = computed(() => store.state.userStore.isValidToken);

    const logout = () => {
      store.dispatch("userStore/userLogout", userInfo.userEmail);
    }

    return {userInfo, isLogin, isValidToken, logout};
  }
}
</Script>