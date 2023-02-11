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
							
							<div class="card overview-sec" v-if="Object.keys(vote_result).length != 0">
								<div class="card-body">
									<h5 class="subs-title">토론 투표 결과</h5>
									<h6>이 토론의 MVP</h6>
									<div class="about-instructor">
										<div class="abt-instructor-img">
											<router-link to="instructor-profile"><img src="../../../../assets/img/user/temp_user1.png" alt="img" class="img-fluid"></router-link>
											<!-- <router-link to="instructor-profile"><img :src="vote_result.mvp_profile" alt="img" class="img-fluid"></router-link> -->
										</div>
										<div class="instructor-detail">
											<h5><router-link to="instructor-profile">{{ vote_result.mvp_name }} </router-link></h5>
										</div>
									</div>
									<br/>

									<h6> 청중 투표 결과 </h6>
									<p v-for="perspective in vote_result.perspective_list">
										{{ perspective.perspective_name }} : {{ Math.round(perspective.percent * 1000)/10 }} %
									</p>
								</div>
							</div>


							

							<div class="card overview-sec" v-if="summary != ''"> 
								<div class="card-body">
									<h5 class="subs-title"> Summary </h5>
									<p> {{ summary }} </p>
								</div>
							</div>

							
							
						</div>	
						
						<div class="col-lg-4">
							<div class="sidebar-sec">
							
								<!-- Video -->
								<div class="video-sec vid-bg">
									<div class="card">
										<div class="card-body">
											<a class="video-thumbnail" data-fancybox="">
												<img class="" src="../../../../assets/img/video.jpg" alt="">
												<!-- <img class="" :src="debate.thumbnail_url" alt=""> -->
											</a>
											<div class="video-details">
												<div class="row gx-2">
													<div class="col-md-6">
														<router-link to="course-wishlist" class="btn btn-wish w-100"><i class="feather-heart"></i> 위시리스트에 넣기 </router-link>
													</div>
													<div class="col-md-6">
														<a href="javascript:;" class="btn btn-wish w-100"><i class="feather-share-2"></i> 공유하기 </a>
													</div>
												</div>
												<router-link to="checkout" class="btn btn-enroll w-100" v-if="debate.state == 'active'"> 지금 입장하기 </router-link>
												<router-link class="btn btn-enroll w-100 disabled" v-else-if="debate.state == 'inactive'" > 준비 중 </router-link>
												<router-link to="checkout" class="btn btn-enroll w-100" v-else-if="debate.state == 'in ready'" > 대기열 입장 </router-link>
												<router-link to="checkout" class="btn btn-dark w-100 disabled" v-else-if="debate.state == 'closed'"> 종료됨 </router-link>
											</div>
										</div>
									</div>
								</div>
								<!-- /Video -->
								
								
								<!-- Features -->
								<div class="card feature-sec" v-if="Object.keys(debate_files).length != 0">
									<div class="card-body">
										<div class="cat-title">
											<h4> 사용된 파일 </h4>
										</div>
										<div class="perspective wrapper pb-2" v-for="(perspective, index) in debate_files" :key ="index">
											<h6 ><img src="../../../../assets/img/icon/chapter.svg" class="me-2" alt=""><strong>{{ perspective.perspective_name }}</strong></h6>
											<ul class="mb-0">
												<li v-for="file in perspective.files">{{ file.file_name }}</li>
											</ul>
											<br/>
										</div>
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
				state : "closed",
				},
			// TODO : 현재 페이지 진입시 debate_id를 통해서 상세정보 요청.	
			debate_detail : {
				description : "시대가 변하면서 가족 형태가 달라지고 있다. 대가족에서 핵가족으로의 변화를 거쳐, 2020년에는 1인 가구가 전체 가구의 31%를 차지하게 되었다. 가족 구성원이 적어지면서 나타난 변화는 반려동물을 보유한 가구의 증가다. 농림축산식품부에서는 2018년을 기준으로, 전국 가구의 29.5%에 해당하는 511만 가구가 반려동물 키우고 있다고 밝혔다. 파악하지 못한 가구를 더하면 그보다 더 많을 것으로 추정된다. 문제는 급증하는 반려동물 가구와 달리, 제도적 준비가 되어 있지 않다는 점이다. 반려동물이 거리에서 타인을 공격하거나 층간 소음을 일으키는 등의 문제는 일상적으로 일어나고 있다. 하지만 형식적인 절차나 권고만 있을 뿐 명확한 조항이나 예방 대책은 미흡하다. 또한, 반려동물 입양이 펫숍이나 지인을 통해 이루어지는 상황이라 현황 파악이 어렵고, 자연히 책임의식도 떨어진다. 유기되는 반려동물은 해마다 증가하고 있는데, 2018년에는 121,077마리에 달했다. 이러한 문제를 해결하기 위하여, 반려동물의 사적 거래를 금지하고 국가에서 관리하는 제도적 준비가 필요하다. 자연히 재원 마련을 위해 반려동물 가구를 대상으로 동물 등록세를 징수해야 하는지가 논란이 되고 있다",
				category : "정치",
				enroll_count : 6,
			},


			// TODO : 종료된 토론(state==closed)에 대해서만 데이터를 로드한다. 일부 API 구현 필요.
			vote_result : {}, 
			debate_files : [],
			summary : '',
        }
    },
    mounted(){
        if(this.debate.state == 'closed'){
			this.getVoteResult();
			this.getDebateFiles();
			this.getSummary();
		}
    },
    methods: {
        async getVoteResult(){
            api.get('/vote/debates/'+this.debate.debate_id)
            .then((response) => {
                if(response.status == 200){
                    console.log(response);
                    this.vote_result = response.data;
                    console.log('특정 토론 청중 평가 결과 로드 완료');
                    
                } else {
                    console.log(response);
                    console.log('정상 조회 실패')
                }
            }).catch((error)=>{
                console.log(error);
            });
        },
		async getDebateFiles(){
			// 토론의 각 패널에서 사용된(업로드된) 파일들
			this.debate_files = [
				{
					perspective_id : 143,
					perspective_name : "반려동물 보유세는 필요하다.",
					files : [
						{file_name : "143번측 파일명 1", file_url : "../../../../assets/img/user/temp_user2.png"},
						{file_name : "143번측 파일명 2", file_url : "../../../../assets/img/user/temp_user3.png"}
					]
				},  	
				{
					perspective_id : 144,
					perspective_name : "반려동물 보유세는 필요하지않다.",
					files : [
						{file_name : "144번측 파일명 1", file_url : "../../../../assets/img/user/temp_user2.png"},
						{file_name : "144번측 파일명 2", file_url : "../../../../assets/img/user/temp_user3.png"}
					]
				}, 

			];
		},
		async getSummary(){
			this.summary = 
			'이건 대충 적은 요약글입니다. 적을 내용이 없어서 한겨레 자율주행 뉴스 글을 막 끌고 왔습니다. 어쩌구 저쩌구 '+
			'전문가들이 레벨 4∼5 수준의 자율주행 기술 구현이 시기상조라고 보는 데에는 몇가지 이유가 있다. 우선 막대한 비용이다. 김일평 삼성화재 자동차보험전략팀장은 “스마트 도로 시설, 모바일 네트워크 등 자율주행 인프라 구축에 천문학적 비용이 필요하다. 이 때문에 완전 자율주행차 시대는 굉장히 늦게 올 것”이라고 내다봤다. 실제 자율주행 기술이 상용화하려면 도로 위의 많은 변수를 통제해야 한다. 자율주행차에 적합한 전용 도로를 깔고 도로를 주행하는 차량 대부분이 사람의 개입을 최소화한 자율차여야 안정적인 기술 구현이 가능하다.'
		}
    },
    
}
</script>