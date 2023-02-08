<template>
    <div class="main-wrapper">
        <layouts></layouts>
       <div class="page-content">
				<div class="container">
					<div class="row">
						
						<studentsidebar></studentsidebar>

						<div class="col-xl-9 col-md-8">	
							<div class="settings-widget profile-details">
								<div class="settings-menu p-0">
									<div class="profile-heading">
										<h3>프로필</h3>
										<p>자유롭게 프로필을 꾸며보세요</p>
									</div>
									<div class="course-group mb-0 d-flex">
										<div class="course-group-img d-flex align-items-center">
											<router-link to="student-profile"><img src="../../../assets/img/user/user11.jpg" alt="" class="img-fluid"></router-link>
											<div class="course-name">
												<h4><router-link to="student-profile">프로필사진</router-link></h4>
												<p>800px 이하의 png, jpg 파일이 허용됩니다.</p>
											</div>
										</div>
										<div class="profile-share d-flex align-items-center justify-content-center">
											<a href="javascript:;" data-bs-toggle="modal" data-bs-target="#editProfileImg" class="btn btn-success">수정</a>
											<a href="javascript:;" class="btn btn-danger">삭제</a>
										</div>
									</div>
									<div class="checkout-form personal-address add-course-info ">
										<div class="personal-info-head">
											<h4>개인 정보</h4>
											<p>개인 정보를 수정할 수 있습니다</p>
										</div>
										<form action="#">
											<div class="row">
                        <div class="col-lg-6">
                          <div class="form-group">
                            <label class="form-control-label">Email</label>
                            <input type="text" class="form-control"  v-model="user.userEmail" placeholder="Email을 입력하세요">
                          </div>
                        </div>
												<div class="col-lg-6">
													<div class="form-group">
														<label class="form-control-label">학교</label>
														<input type="text" class="form-control" v-model="user.userEmail" placeholder="학교를 입력하세요">
													</div>
												</div>
												<div class="col-lg-6">
													<div class="form-group">
														<label class="form-control-label">학년</label>
														<input type="text" class="form-control" v-model="user.grade" placeholder="학년을 입력하세요">
													</div>
												</div>
												<div class="col-lg-6">
													<div class="form-group">
														<label class="form-control-label">반</label>
														<input type="text" class="form-control" v-model="user.classNum" placeholder="반을 입력하세요">
													</div>
												</div>
                        <div class="col-lg-6">
                          <div class="form-group">
                            <label class="form-control-label">직위</label>
                            <input type="text" class="form-control" v-model="user.position" placeholder="반을 입력하세요">
                          </div>
                        </div>
<!--												<div class="col-lg-6">-->
<!--													<div class="form-group">-->
<!--														<label  class="form-label">Country</label>-->
<!--                                                        <vue-select :options="country" placeholder="Select country" name="sellist1" />-->
<!--													</div>-->
<!--												</div>-->
<!--												<div class="col-lg-6">-->
<!--													<div class="form-group">-->
<!--														<label class="form-control-label">Address Line 1</label>-->
<!--														<input type="text" class="form-control" placeholder="Address">-->
<!--													</div>-->
<!--												</div>-->
<!--												<div class="col-lg-6">-->
<!--													<div class="form-group">-->
<!--														<label class="form-control-label">Address Line 2 (Optional)</label>-->
<!--														<input type="text" class="form-control" placeholder="Address">-->
<!--													</div>-->
<!--												</div>-->
<!--												<div class="col-lg-6">-->
<!--													<div class="form-group">-->
<!--														<label class="form-control-label">City</label>-->
<!--														<input type="text" class="form-control" placeholder="Enter your City">-->
<!--													</div>-->
<!--												</div>-->
<!--												<div class="col-lg-6">-->
<!--													<div class="form-group">-->
<!--														<label class="form-control-label">ZipCode</label>-->
<!--														<input type="text" class="form-control" placeholder="Enter your Zipcode" >-->
<!--													</div>-->
<!--												</div>-->
												<div class="update-profile">
													<button type="button" class="btn btn-primary">프로필 수정</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>	
						<!-- Profile Details -->
						
					</div>
				</div>
			</div>	
			<!-- /Dashbord Student -->
        
        <layouts1></layouts1>
       
    </div>
    <!-- /Main Wrapper -->
  <editProfileImg></editProfileImg>
</template>
<script>
    export default {
      components: {
        
      },
      data() {
            return {
              user: {
                userEmail: '',
                name: '',
                department: '',
                grade: '',
                classNum: ''
              },


            }
        },
      methods:{
        // async changeUserInfo() {
        //   if(this.userEmail != null && this.name != null
        //       && this.department != null && this.grade!= null && this.classNum != null) {
        //     const api = apiInstance();
        //     api.defaults.headers["authorization"] = "Bearer " + this.token;
        //     await api.patch("/users/info", this.user)
        //         .then(response => {
        //           if (response.status == 200) {
        //             alert("비밀번호 변경에 성공하셨습니다.")
        //             this.$router.push('/')
        //           } else if (response.status == 404) {
        //             this.$router.push('/error-404')
        //           } else if (response.status == 500) {
        //             this.$router.push('/error-500')
        //           }
        //         });
        //   } else if(this.password == null || this.passwordConfirm == null) {
        //     alert("비밀번호를 입력해 주세요")
        //   }else if(this.password != this.passwordConfirm){
        //     alert("비밀번호가 같지 않습니다.")
        //   }
        // }
      },
        mounted() {
          //this.user.userEmail = this.$route.params.userEmail;
          //this.user.name = this.$route.params.name;
          //this.user.department = this.$route.params.department;
          //this.user.grade = this.$route.params.grade;
          //this.user.classNum = this.$route.params.classNum;
        }
    }
</script>
