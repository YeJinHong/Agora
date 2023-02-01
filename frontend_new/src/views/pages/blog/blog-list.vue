<template>
    <!-- Main Wrapper -->
    <div class="main-wrapper">
        
        <layoutslogin></layoutslogin>
        <bloglist></bloglist>
        <!-- Course -->
        <section class="course-content">
            <div class="container">
                <div class="row">
                    <div class="col-lg-9 col-md-12">

                        <!-- Blog Post -->
                        <div class="blog">
                            <div class="blog-image">
                                <router-link to="blog-details"><img class="img-fluid" src="../../../assets/img/blog/blog-05.jpg" alt="Post Image"></router-link>
                            </div>
                            <div class="blog-info clearfix">
                                <div class="post-left">
                                    <ul>
                                        <li>
                                            <div class="post-author">
                                                <router-link to="instructor-profile"><img src="../../../assets/img/user/user.jpg" alt="Post Author"> <span>Ruby Perrin</span></router-link>
                                            </div>
                                        </li>
                                        <li><img class="img-fluid" src="../../../assets/img/icon/icon-22.svg" alt="">April 20, 2022</li>
                                        <li><img class="img-fluid" src="../../../assets/img/icon/icon-23.svg" alt="">Programming, Web Design</li>
                                    </ul>
                                </div>
                            </div>
                            <h3 class="blog-title"><router-link to="blog-details">Learn Webs Applications Development from Experts</router-link></h3>
                            <div class="blog-content blog-read">
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Phasellus hendrerit. Pellentesque aliquet nibh nec urna. In nisi neque, aliquet vel, dapibus id, mattis vel, nisi. Sed pretium, ligula sollicitudin laoreet viverra, tortor libero sodales leo, eget blandit nunc tortor eu nibh. Nullam mollis. Ut justo. Suspendisse potenti. Sed egestas, ante et vulputate volutpat, eros pede […]</p>
                                <router-link to="blog-details" class="read-more btn btn-primary">Read More</router-link>
                            </div>
                        </div>
                        <!-- /Blog Post -->
                        
                        <!-- Blog Post -->
                        <div class="blog">
                            <div class="blog-image">
                                <router-link to="blog-details"><img class="img-fluid" src="../../../assets/img/blog/blog-06.jpg" alt="Post Image"></router-link>
                            </div>
                            <div class="blog-info clearfix">
                                <div class="post-left">
                                    <ul>
                                        <li>
                                            <div class="post-author">
                                                <router-link to="instructor-profile"><img src="../../../assets/img/user/user1.jpg" alt="Post Author"> <span>Jenis R.</span></router-link>
                                            </div>
                                        </li>
                                        <li><img class="img-fluid" src="../../../assets/img/icon/icon-22.svg" alt="">May 20, 2021</li>
                                        <li><img class="img-fluid" src="../../../assets/img/icon/icon-23.svg" alt="">Programming, Courses</li>
                                    </ul>
                                </div>
                            </div>
                            <h3 class="blog-title"><router-link to="blog-details">Expand Your Career Opportunities With Python</router-link></h3>
                            <div class="blog-content blog-read">
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Phasellus hendrerit. Pellentesque aliquet nibh nec urna. In nisi neque, aliquet vel, dapibus id, mattis vel, nisi. Sed pretium, ligula sollicitudin laoreet viverra, tortor libero sodales leo, eget blandit nunc tortor eu nibh. Nullam mollis. Ut justo. Suspendisse potenti. Sed egestas, ante et vulputate volutpat, eros pede […]</p>
                                <router-link to="blog-details" class="read-more btn btn-primary">Read More</router-link>
                            </div>
                        </div>
                        <!-- /Blog Post -->
                        
                        <!-- Blog Post -->
                        <div class="blog">
                            <div class="blog-image">
                                <router-link to="blog-details"><img class="img-fluid" src="../../../assets/img/blog/blog-07.jpg" alt="Post Image"></router-link>
                            </div>
                            <div class="blog-info clearfix">
                                <div class="post-left">
                                    <ul>
                                        <li>
                                            <div class="post-author">
                                                <router-link to="instructor-profile"><img src="../../../assets/img/user/user3.jpg" alt="Post Author"> <span>Rolands R</span></router-link>
                                            </div>
                                        </li>
                                        <li><img class="img-fluid" src="../../../assets/img/icon/icon-22.svg" alt="">Jun 14, 2022</li>
                                        <li><img class="img-fluid" src="../../../assets/img/icon/icon-23.svg" alt="">Programming, Web Design</li>
                                    </ul>
                                </div>
                            </div>
                            <h3 class="blog-title"><router-link to="blog-details">Complete PHP Programming Career Guideline</router-link></h3>
                            <div class="blog-content blog-read">
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Phasellus hendrerit. Pellentesque aliquet nibh nec urna. In nisi neque, aliquet vel, dapibus id, mattis vel, nisi. Sed pretium, ligula sollicitudin laoreet viverra, tortor libero sodales leo, eget blandit nunc tortor eu nibh. Nullam mollis. Ut justo. Suspendisse potenti. Sed egestas, ante et vulputate volutpat, eros pede […]</p>
                                <router-link to="blog-details" class="read-more btn btn-primary">Read More</router-link>
                            </div>
                        </div>
                        <!-- /Blog Post -->

                        <pagination></pagination>
                        
                    </div>
                    
                    <blogsidebar></blogsidebar>
                    
                </div>
            </div>
        </section>
        <!-- /Course -->

        <layouts1></layouts1>
       
    </div>
    <!-- /Main Wrapper -->
</template>