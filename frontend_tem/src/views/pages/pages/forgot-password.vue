<template>
    <!-- Main Wrapper -->
    <div class="main-wrapper">
        
        <div class="row">
			
            <loginbanner></loginbanner>
            
            <div class="col-md-6 login-wrap-bg">		
            
                <!-- Login -->
                <div class="login-wrapper">
                    <div class="loginbox">
                        <div class="img-logo">
                            <img src="../../../assets/img/logo.svg" class="img-fluid" alt="Logo">
                            <div class="back-home">
                                <router-link to="/">Back to Home</router-link>
                            </div>
                        </div>
                        <h1>Forgot Password ?</h1>
                        <div class="reset-password">
                            <p>Enter your email to reset your password.</p>
                        </div>
                            <div class="form-group">
                                <label class="form-control-label">Email</label>
                                <input type="email" class="form-control" v-model:userEmail placeholder="Enter your email address">
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-start" @click="sendEmail" type="submit">Submit</button>
                            </div>
                    </div>
                </div>
                <!-- /Login -->
                
            </div>
            
        </div>
       
    </div>
    <!-- /Main Wrapper -->
</template>

<script>
import { apiInstance } from "../../../api/index.js";

export default {
  data(){
    return {
      userEmail:''
    }
  },
  methods:{
    sendEmail(){
      if(userEmail != ''){
        const api = apiInstance();
        api.post('/users/email',
            {
              userEmail : this.userEmail
            }).then(response => {
              alert("이메일이 전송되었습니다." +
                  "30분 내로 변경 가능합니다.")
        }).catch(error => {
          alert("이메일 변경에 실패 하셨습니다." )
        })
      }
    }

  }
}


</script>
