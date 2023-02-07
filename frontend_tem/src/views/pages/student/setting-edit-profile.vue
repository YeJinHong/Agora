<template>
    <!-- Main Wrapper -->
    <div class="main-wrapper">
        
        <layouts></layouts>
       
            <!--Dashbord Student -->
			<div class="page-content">
				<div class="container">
					<div class="row">
						
						<studentsidebar></studentsidebar>
						
						<!-- Profile Details -->
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
											<a href="javascript:;" class="btn btn-success">수정</a>
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
                            <input type="text" class="form-control" placeholder="Email을 입력하세요">
                          </div>
                        </div>
												<div class="col-lg-6">
													<div class="form-group">
														<label class="form-control-label">학교</label>
														<input type="text" class="form-control" placeholder="학교를 입력하세요">
													</div>
												</div>
												<div class="col-lg-6">
													<div class="form-group">
														<label class="form-control-label">학년</label>
														<input type="text" class="form-control" placeholder="학년을 입력하세요">
													</div>
												</div>
												<div class="col-lg-6">
													<div class="form-group">
														<label class="form-control-label">반</label>
														<input type="text" class="form-control" placeholder="반을 입력하세요">
													</div>
												</div>
                        <div class="col-lg-6">
                          <div class="form-group">
                            <label class="form-control-label">직위</label>
                            <select class="form-select" style="border: #e9ecef 1px solid" >
                              <!-- <option selected></option> -->
                              <option value="1">학생</option>
                              <option value="2">선생님</option>
                            </select>
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
</template>
<script>
    import Vue from 'vue'
    export default {
      components: {
        
      },
      data() {
            return {
                country: ["Select country", "India", "America", "London"],
               
            }
        },
        mounted() {

        }
    }
</script>
