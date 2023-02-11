<template>
    <!-- Main Wrapper -->
    <div class="main-wrapper">
        
        <layouts2></layouts2>
        <coursedetails :debate="debate"></coursedetails>
        <inner-page :debate="debate" :debate_detail="debate_detail"></inner-page>
            <!-- Course Content -->
			<section class="page-content course-sec">
				<div class="container">
				
					<div class="row">
						<div class="col-lg-8">
						
							<!-- Overview -->
							<div class="card overview-sec">
								<div class="card-body">
									<h5 class="subs-title">Overview</h5>
									<h6>토론 설명</h6>
									<p>{{ debate_detail.description }}</p>
									<br/>
									<h6>관련 칼럼</h6>
									<ul class="mb-0">
										<li><a href="https://www.hani.co.kr/arti/animalpeople/companion_animal/1055181.html">[한겨레] “반려동물 보유세 어떻게 생각하시나요” 국민에 묻는다</a></li>
										<li><a href="https://www.sejungilbo.com/news/articleView.html?idxno=39319"> [세정일보] [칼럼] ‘반려동물 보유세’ 도입 시급하다</a></li>
										<li class="mb-0"><a href="https://namu.wiki/w/%EB%B0%98%EB%A0%A4%EB%8F%99%EB%AC%BC%20%EB%B3%B4%EC%9C%A0%EC%84%B8">[나무위키] 반려동물 보유세</a></li>
									</ul>
								</div>
							</div>
							<!-- /Overview -->
							
							
							<!-- Reviews -->
							<div class="card review-sec">
								<div class="card-body">
									<h5 class="subs-title">Reviews</h5>
									<div class="review-wrapper pb-5" v-for="reviewer in reviewers">
										<div class="instructor-wrap">
											<div class="about-instructor">
												<div class="abt-instructor-img">
													<router-link to="instructor-profile"><img src="../../../../assets/img/user/user1.jpg" alt="img" class="img-fluid"></router-link>
												</div>
												<div class="instructor-detail">
													<h5><router-link to="instructor-profile">{{ reviewer.user_name}}</router-link></h5>
													<p>{{ reviewer.department_position }}</p>
												</div>
											</div>
											<div class="rating">							
												<i class="fas fa-star filled me-1"></i>
												<i class="fas fa-star filled me-1"></i>
												<i class="fas fa-star filled me-1"></i>
												<i class="fas fa-star filled me-1"></i>
												<i class="fas fa-star filled me-1"></i>
												<span class="d-inline-block average-rating"> {{ reviewer.rating }}</span>
											</div>
										</div>
										<p class="rev-info">{{ reviewer.content }}</p>
										<!-- <a href="javascript:;" class="btn btn-reply"><i class="feather-corner-up-left"></i> Reply</a> -->
									</div>
								</div>

								
							</div>
							<!-- /Reviews -->
							
							<!-- Comment -->
							<div class="card comment-sec">
								<div class="card-body">
									<h5 class="subs-title"> 댓글 달기 </h5>
									<form>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<input type="text" class="form-control" placeholder="Full Name">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<input type="email" class="form-control" placeholder="Email">
												</div>
											</div>
										</div>
										<div class="form-group">
											<input type="email" class="form-control" placeholder="Subject">
										</div>
										<div class="form-group">
											<textarea rows="4" class="form-control" placeholder="Your Comments"></textarea>
										</div>
										<div class="submit-section">
											<button class="btn submit-btn" type="submit">Submit</button>
										</div>
									</form>
								</div>
							</div>
							<!-- /Comment -->
							
						</div>	
						
						<div class="col-lg-4">
							<div class="sidebar-sec">
							
								<!-- Video -->
								<div class="video-sec vid-bg">
									<div class="card">
										<div class="card-body">
											<a href="https://www.youtube.com/embed/1trvO6dqQUI" class="video-thumbnail" data-fancybox="">
												<div class="play-icon">
													<i class="fa-solid fa-play"></i>
												</div>
												<img class="" src="../../../../assets/img/video.jpg" alt="">
											</a>
											<div class="video-details">
												<div class="course-fee">
													<h2>{{debate.state}}</h2>
												</div>
												<div class="row gx-2">
													<div class="col-md-6">
														<router-link to="course-wishlist" class="btn btn-wish w-100"><i class="feather-heart"></i> 위시리스트에 넣기 </router-link>
													</div>
													<div class="col-md-6">
														<a href="javascript:;" class="btn btn-wish w-100"><i class="feather-share-2"></i> 공유하기 </a>
													</div>
												</div>
												<router-link to="checkout" class="btn btn-enroll w-100">지금 참여하기</router-link>
											</div>
										</div>
									</div>
								</div>
								<!-- /Video -->
								
								
								<!-- Features -->
								<div class="card feature-sec">
									<div class="card-body">
										<div class="cat-title">
											<h4>Includes</h4>
										</div>
										<ul>
											<li><img src="../../../../assets/img/icon/users.svg" class="me-2" alt=""> Enrolled: <span>32 students</span></li>
											<li><img src="../../../../assets/img/icon/timer.svg" class="me-2" alt=""> Duration: <span>20 hours</span></li>
											<li><img src="../../../../assets/img/icon/chapter.svg" class="me-2" alt=""> Chapters: <span>15</span></li>
											<li><img src="../../../../assets/img/icon/video.svg" class="me-2" alt=""> Video:<span> 12 hours</span></li>
											<li><img src="../../../../assets/img/icon/chart.svg" class="me-2" alt=""> Level: <span>Beginner</span></li>
										</ul>
									</div>
								</div>
								<!-- /Features -->
								
							</div>
						</div>
					</div>	
				</div>
			</section>
			<!-- /Pricing Plan -->
        <layouts1></layouts1>
       
    </div>
    <!-- /Main Wrapper -->
</template>

<script>    

import { apiInstance } from "/api/index.js";      

const api = apiInstance();

export default {
    setup(){
		
    },
    data(){
        return {
			debate : {
				debate_id : 1,
				debate_mode : "CEDA",
				debate_title : "반려동물 보유세 필요한가?",
				call_start_time : "2023-01-11 13:00:00",
				call_end_time : "2023-01-11 16:00:00",
				thumbnail_url : "../../../../assets/img/course/testImg.jpg",
				owner_email : "ssafy@naver.com",
				owner_name : "김싸피",	
				owner_profile : "../../../../assets/img/user/temp_user1.png",
				owner_department_position : "싸피고등학교 선생님",
				state : "inactive",
				},
			// TODO : 현재 페이지 진입시 debate_id를 통해서 상세정보 요청.	
			debate_detail : {
				description : "시대가 변하면서 가족 형태가 달라지고 있다. 대가족에서 핵가족으로의 변화를 거쳐, 2020년에는 1인 가구가 전체 가구의 31%를 차지하게 되었다. 가족 구성원이 적어지면서 나타난 변화는 반려동물을 보유한 가구의 증가다. 농림축산식품부에서는 2018년을 기준으로, 전국 가구의 29.5%에 해당하는 511만 가구가 반려동물 키우고 있다고 밝혔다. 파악하지 못한 가구를 더하면 그보다 더 많을 것으로 추정된다. 문제는 급증하는 반려동물 가구와 달리, 제도적 준비가 되어 있지 않다는 점이다. 반려동물이 거리에서 타인을 공격하거나 층간 소음을 일으키는 등의 문제는 일상적으로 일어나고 있다. 하지만 형식적인 절차나 권고만 있을 뿐 명확한 조항이나 예방 대책은 미흡하다. 또한, 반려동물 입양이 펫숍이나 지인을 통해 이루어지는 상황이라 현황 파악이 어렵고, 자연히 책임의식도 떨어진다. 유기되는 반려동물은 해마다 증가하고 있는데, 2018년에는 121,077마리에 달했다. 이러한 문제를 해결하기 위하여, 반려동물의 사적 거래를 금지하고 국가에서 관리하는 제도적 준비가 필요하다. 자연히 재원 마련을 위해 반려동물 가구를 대상으로 동물 등록세를 징수해야 하는지가 논란이 되고 있다",
				category : "정치",
				enroll_count : 6,
			},
			// TODO : 해당 토론에 달린 댓글들. 구현된 API X. 페이지가 너무 허해서 넣음.
			reviewers : [
				{user_email : "fake@naver.com", user_name : "김리뷰어", user_profileUrl : "../../../../assets/img/user/temp_user1.png", department_position : "싸피고등학교", rating : "5.0", 	content : "정말 기대가 됩니다.\n 우리 생활에 꼭 필요한 토론 주제입니다!"},
				{user_email : "debate@naver.com", user_name : "박토론", user_profileUrl : "../../../../assets/img/user/temp_user2.png", department_position : "싸피고등학교", rating : "5.0", 	content : "재밌는 주제입니다! 좋다."},
				{user_email : "news@naver.com", user_name : "신 문", user_profileUrl : "../../../../assets/img/user/temp_user3.png", department_position : "싸피고등학교", rating : "5.0", 	content : "안녕하세요. 즐거운 토론해요."},
			]

        }
    },
    mounted(){

    },
    methods: {

    },
    
}
</script>