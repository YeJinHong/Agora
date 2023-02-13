<template>
	<!-- Main Wrapper -->
	<div class="main-wrapper">
  
	  <layouts2></layouts2>
	  <coursegridbreadcrumb></coursegridbreadcrumb>
	  <!-- Course -->
	  <section class="course-content">
		<div class="container">
		  <div class="row">
			<div class="col-lg-9">
  
			  <coursefilter></coursefilter>
  
			  <div class="row">
				<div class="col-lg-12 col-md-12 d-flex" v-for="(debate, index) in data.debate_list">
				  <div class="course-box course-design list-course d-flex">
					<div class="product">
					  <div class="product-img">
						<router-link to="course-details">
						  <img class="img-fluid" alt="" src="../../../../assets/img/course/testImg.jpg" @click="setSelectedDebateId(debate.id)">
						</router-link>
						<div class="price">
						  <!-- <h3>$300 <span>$99.00</span></h3> -->
						  <h3 :class="debate.state">{{ debate.state }}</h3>
						</div>
					  </div>
					  <div class="product-content">
						<div class="head-course-title">
						  <h3 class="title">
							<router-link to="course-details">{{ debate.title }}</router-link>
						  </h3>
						  <div class="all-btn all-category d-flex align-items-center">
							<router-link to="checkout" class="btn btn-primary">상세 보기</router-link>
						  </div>
						</div>
						<div class="course-info border-bottom-0 pb-0 align-items-center">
							<div class="rating-img d-flex align-items-center pb-2">
								<img src="../../../../assets/img/icon/icon-01.svg" alt="">
								<p> Mode : {{ debate.debateMode }}</p>
							</div>
							<div class="course-view d-flex align-items-center  pl-2">
								<img src="../../../../assets/img/icon/icon-02.svg" alt="">
								<p>{{ debate.callStartTime }} ~ {{ debate.callEndTime }}</p>
						  	</div>
						</div>
						<div class="course-group d-flex mb-0">
						  <div class="course-group-img d-flex">
							<!-- <router-link to="instructor-profile"><img :src="debate.owner_profile" alt="" class="img-fluid"></router-link> -->
							<router-link to="instructor-profile"><img src="../../../../assets/img/user/temp_user2.png"
																	  alt="" class="img-fluid"></router-link>
							<!-- <router-link to="instructor-profile"><img :src="debate.owner_profile" alt="" class="img-fluid"></router-link> -->
  
							<div class="course-name">
							  <h4>
								<router-link to="instructor-profile">{{ debate.ownerId }} 이것은 유저의 ID. API 수정 필요.</router-link>
							  </h4>
							  <p>{{ debate.ownerDepartment }}</p>
							</div>
						  </div>
						  <div class="course-share d-flex align-items-center justify-content-center">
							<a href="javascript:;"><i class="fa-regular fa-heart"></i></a>
						  </div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
			  </div>
  
			  <pagination></pagination>
  
			</div>
			<gridlistsidebar></gridlistsidebar>
		  </div>
		</div>
	  </section>
	  <!-- /Course -->
  
	  <layouts1></layouts1>
  
	</div>
	<!-- /Main Wrapper -->
  </template>
  <script>
  
  import {apiInstance} from "/api/index.js";
  import {useStore} from "vuex";
  import {computed, onMounted, reactive, watch} from "vue";
  import {useRouter} from "vue-router";
  
  const api = apiInstance();
  
  export default {
	setup() {
	  const store = useStore();
	  const router = useRouter();
	  const data = reactive({
		debate_list : [],
		
	  })
	  
	  watch(
		  // pretend you have a getData getter in store
		  () => store.getters["debate/getDebateList"],
		  (val, oldVal) => {
			data.debate_list = store.getters["debate/getDebateList"];
		  }
	  )

	  // TODO : 토론 목록에서 Debate ID 정보가 추가로 필요함.
	  function setSelectedDebateId(debate_id){
		console.log(debate_id);
		console.log(debate_id == undefined);
		console.log(debate_id == '');
		if(debate_id == undefined) debate_id = 3;
		store.dispatch('debate/setSelectedDebateId', debate_id)
	}
  
	  return {data, setSelectedDebateId}
	},
	data() {
	  return {
		owners: [],
	  }
	},
	mounted() {
  
	},
	methods: {
		
	},
  
  }
  </script>