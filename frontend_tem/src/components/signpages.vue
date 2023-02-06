<template>
  <ul class="nav header-navbar-rht">
      <li class="nav-item">
        <router-link class="nav-link header-sign" to="login">로그인</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link header-login" to="register">회원가입</router-link>
      </li>
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
}
</script>