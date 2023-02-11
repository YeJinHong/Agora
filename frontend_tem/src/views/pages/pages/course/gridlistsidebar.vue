<template>
    <div class="col-lg-3 theiaStickySidebar">
        <div class="stickysidebar">
            <div class="filter-clear">
                <div class="clear-filter d-flex align-items-center">
                    <h4><i class="feather-filter"></i>Filters</h4>
                    <div class="clear-text">
                        <p>CLEAR</p>
                    </div>
                </div>
                
                <!-- Search Filter -->
                <div class="card search-filter">
                    <div class="card-body">
                        <div class="filter-widget mb-0">
                            <div class="categories-head d-flex align-items-center">
                                <h4> 토론 카테고리 </h4>
                                <i class="fas fa-angle-down"></i>
                            </div>
                            <div v-for="category in categories">
                                <label class="custom_check">
                                    <input type="checkbox" name="select_specialist" >
                                    <span class="checkmark"></span> {{ category.codeName }}

                                </label>
                            </div>
                            
                        </div>
                    </div>
                </div>
                <!-- /Search Filter -->
                
                <!-- Latest Posts -->
                <div class="card post-widget ">
                    <div class="card-body">
                        <div class="latest-head">
                            <h4 class="card-title">Latest Courses</h4>
                        </div>
                        <ul class="latest-posts">
                            <li>
                                <div class="post-thumb">
                                    <router-link to="course-details">
                                        <img class="img-fluid" src="../../../../assets/img/blog/blog-01.jpg" alt="">
                                    </router-link>
                                </div>
                                <div class="post-info free-color">
                                    <h4>
                                        <router-link to="course-details">Introduction LearnPress – LMS plugin</router-link>
                                    </h4>
                                    <p>FREE</p>
                                </div>
                            </li>
                            <li>
                                <div class="post-thumb">
                                    <router-link to="course-details">
                                        <img class="img-fluid" src="../../../../assets/img/blog/blog-02.jpg" alt="">
                                    </router-link>
                                </div>
                                <div class="post-info">
                                    <h4>
                                        <router-link to="course-details">Become a PHP Master and Make Money</router-link>
                                    </h4>
                                    <p>$200</p>
                                </div>
                            </li>
                            
                        </ul>
                    </div>
                </div>
                <!-- /Latest Posts -->
            
            </div>
        </div>
    </div>
</template>

<script>    

import { apiInstance } from "/api/index.js";      

const api = apiInstance();

export default {
    setup(){

    },
    data(){
        return {
			categories : [],
        }
    },
    mounted(){
        this.getCategories();
    },
    methods: {
        async getCategories(){
            api.get(`/codes/category`)
			.then((data) => {
				let result = data["data"].data;
				console.log(result);
				this.categories = result;
			})
			.catch((error) => {
				alert("error : " + error.code);
			})
        }
    },
    
}
</script>