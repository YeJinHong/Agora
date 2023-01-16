<template>
  <el-dialog custom-class="login-dialog" title="로그인" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align" @keyup="isValid">
      <el-form-item prop="user_id" label="아이디" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.user_id" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="clickLogin" :disabled="state.form.isFormValid">로그인</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<style>
.login-dialog {
  width: 400px !important;
  height: 300px;
}
.login-dialog .el-dialog__headerbtn {
  float: right;
}
.login-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.login-dialog .el-form-item {
  margin-bottom: 20px;
}
.login-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.login-dialog .el-input__suffix {
  display: none;
}
.login-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.login-dialog .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import accountStore from "@/store/accountStore";

export default {
  name: 'login-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },


  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const loginForm = ref(null)

    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      form: {
        user_id: '',
        password: '',
        align: 'left',
        isFormValid: true
      },
      rules: {
        user_id: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 0, max: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 9, message: '최소 9글자를 입력해야 합니다.', trigger: 'blur' },
          { max: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'blur' },
          { pattern: new RegExp(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{9,16}$/), message: '비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다.', trigger: 'blur' },
        ]
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
       console.log(loginForm.value)
    })

    const clickLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate(async (valid) => {
        if (valid) {
          console.log(valid);
          console.log('submit')
          await store.dispatch('accountStore/loginAction', { user_id: state.form.user_id, password: state.form.password })
          console.log(store)
          console.log('accessToken ' + store.getters['accountStore/getToken'])

          if(store.getters['accountStore/getToken'] != null){
            // location.reload();
            handleClose();
          }else{
            alert("로그인 혹은 비밀번호가 틀렸습니다.")
          }
        } else {
          console.log(valid);
          handleClose();
          alert('Validate error!')
        }
      });
    }

    const handleClose = function () {
      state.form.user_id = ''
      state.form.password = ''
      emit('closeLoginDialog')
    }

    // keyup으로 form 값 변경시 validation 확인.
    const isValid = () => {
      loginForm.value.validate((valid) => {
        if(valid){
          console.log(valid);
          state.form.isFormValid = false;
          return true;
        } else {
          state.form.isFormValid = true;
          console.log(valid);
          return false;
        }
      })
    }

    return { loginForm, state, clickLogin, handleClose, isValid}
  },

}
</script>
