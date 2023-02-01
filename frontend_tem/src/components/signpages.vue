<template>
  <ul class="nav header-navbar-rht">
    <div style=" display: contents;" v-if="userInfo !== null && isLogin && isValidToken">
      <li class="nav-item">
        <router-link class="nav-link header-sign" to="login">마이페이지</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link header-login" to="register">로그아웃</router-link>
      </li>
    </div>
    <div style=" display: contents;" v-else>
      <li class="nav-item">
        <router-link class="nav-link header-sign" to="login">로그인</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link header-login" to="register">회원가입</router-link>
      </li>
    </div>


  </ul>
</template>
<script>
import Vue, {reactive, computed, ref, onMounted, watch} from 'vue';
import {useStore} from 'vuex';
import {useRouter, useRoute} from 'vue-router';

export default {
  name: 'signpages',
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
</script>