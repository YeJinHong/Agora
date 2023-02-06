<template>
<!-- Latest Blog -->
<section class="section latest-blog">
    <div class="container">
        <div class="section-header aos" data-aos="fade-up">
            <div class="section-sub-head feature-head text-center mb-0">
                <h2>Latest Blogs</h2>
                <div class="section-text aos" data-aos="fade-up">
                    <p class="mb-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Eget aenean accumsan bibendum gravida maecenas augue elementum et neque. Suspendisse imperdiet.</p>
                </div>
            </div>
        </div>
        <Carousel :settings="settings" :breakpoints="breakpoints">
            <Slide v-for="item in latest" :key="item.id" class="owl-carousel blogs-slide owl-theme">
                <div class="instructors-widget blog-widget">
                    <div class="instructors-img">
                        <router-link to="blog-list">
                            <img class="img-fluid" alt="" :src="loadImg(item.img)">
                        </router-link>
                    </div>
                    <div class="instructors-content text-center">
                        <h5><router-link to="blog-list">{{item.head}}</router-link></h5>
                        <p>{{item.place}}</p>
                        <div class="student-count d-flex justify-content-center">
                            <i class="fa-solid fa-calendar-days"></i>
                            <span>{{item.date}}</span>
                        </div>
                    </div>
                </div>
            
            </Slide>
            <template #addons>
                <Pagination />
            </template>
        </Carousel>
        <div class="enroll-group aos" data-aos="fade-up">
            <div class="row ">
                <div class="col-lg-4 col-md-6">
                    <div class="total-course d-flex align-items-center">
                        <div class="blur-border">
                            <div class="enroll-img ">
                                <img src="../../../assets/img/icon/icon-07.svg" alt="" class="img-fluid">
                            </div>
                        </div>
                        <div class="course-count">
                            <h3><span><vue3-autocounter class="counter" ref='counter' :startAmount='15000' :delay='3' :endAmount='253085' :duration='5' :autoinit='true' /></span></h3>
                            <p>STUDENTS ENROLLED</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="total-course d-flex align-items-center">
                        <div class="blur-border">
                            <div class="enroll-img ">
                                <img src="../../../assets/img/icon/icon-08.svg" alt="" class="img-fluid">
                            </div>
                        </div>
                        <div class="course-count">
                            <h3><span><vue3-autocounter class="counter" ref='counter' :startAmount='205' :delay='3' :endAmount='1205' :duration='5' :autoinit='true' /></span></h3>
                            <p>TOTAL COURSES</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="total-course d-flex align-items-center">
                        <div class="blur-border">
                            <div class="enroll-img ">
                                <img src="../../../assets/img/icon/icon-09.svg" alt="" class="img-fluid">
                            </div>
                        </div>
                        <div class="course-count">
                            <h3><span><vue3-autocounter class="counter" ref='counter' :startAmount='20' :delay='3' :endAmount='127' :duration='5' :autoinit='true' /></span></h3>
                            <p>COUNTRIES</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="lab-course">
            <div class="section-header aos" data-aos="fade-up">
                <div class="section-sub-head feature-head text-center">
                    <h2>Unlimited access to 360+ courses <br>and 1,600+ hands-on labs</h2>
                </div>
            </div>
            <div class="icon-group aos" data-aos="fade-up">
                <div class="offset-lg-1 col-lg-12">
                    <div class="row">
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-09.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-10.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-16.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-12.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-13.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-14.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-15.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-16.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-17.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1 col-3">
                            <div class="total-course d-flex align-items-center">
                                <div class="blur-border">
                                    <div class="enroll-img ">
                                        <img src="../../../assets/img/icon/icon-18.svg" alt="" class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>	
<!-- /Latest Blog -->
</template>
<script>
    import AOS from 'aos';
    import latest from '../../../assets/json/latest.json'
    import { defineComponent } from 'vue'
    import { Carousel, Pagination, Slide } from 'vue3-carousel';
    const images = require.context('../../../assets/img/blog', false, /\.png$|\.jpg$/)
    import 'vue3-carousel/dist/carousel.css';
    export default {
        name: 'Autoplay',
            components: {
            Carousel,
            Slide,
            Pagination,
            },
        data() {
            return {
                latest : latest
            }
        },
        methods:{
        loadImg(imgPath) {
            return images('./' + imgPath).default
        },
    },
    setup() {
		return {
			// carousel settings
			settings: {
				itemsToShow: 1,
				snapAlign: "center"
			},
			breakpoints: {
				// 700px and up
				700: {
					itemsToShow: 2,
					snapAlign: "center"
				},
				// 1024 and up
				1024: {
					itemsToShow: 4,
					snapAlign: "start"
				}
			}
		};
	},

        mounted() {
            if($('.main-wrapper .aos').length > 0) {
            AOS.init({
              duration: 1200,
              once: true,
            });
        }
       
        },
       
    }
    </script>