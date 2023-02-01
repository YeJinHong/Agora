<template>

    <!-- TODO : UI 수정 -->
    <div v-if="evaluation_open" class="evaluationmodal">
        <h4 class="m-3">"{{debateTitle}}" 상호평가 </h4>
        <hr/>
        <div class="m-2">
            <div v-for="item in evaluation_list">
                <div class="all-btn all-category d-flex align-items-center" >
                    <div class="btn btn-primary" @click="evaluated_selected = !evaluated_selected"> {{ item.name }} </div>
                </div>
            </div>
        </div>
        <button @click="closeEvaluation()">평가 완료하기</button>
    </div>

    <div v-if="evaluated_selected" class="selectedmodal">
        <h5> {{ selected }}</h5>
        <div v-for = "item in evaluation_category_list">
            <li> {{ item.description }} </li>
            <select name="point">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
        <button @click="evaluated_selected = false">평가 제출</button>
    </div>

    <!-- Main Wrapper -->
    <div class="main-wrapper">
        
        <layoutslogin></layoutslogin>
       
        <blogdetails></blogdetails>

        <!-- Blog Details -->
        <section class="course-content">
                    

            <div class="container">
                <div class="row">
                    <div class="col-lg-9 col-md-12">

                        <!-- Blog Post -->
                        <div class="blog">
                            <div class="blog-image">
                                <router-link to="blog-details"><img class="img-fluid" src="../../../assets/img/blog-banner.jpg" alt="Post Image"></router-link>
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
                            <h3 class="blog-title"><router-link to="blog-details">Hi! I'm Temp Page!!!!!</router-link></h3>
                            <div class="blog-content">
                                <div class="all-btn all-category d-flex align-items-center" >
                                    <div class="btn btn-primary" @click="this.evaluation_open = true"> Open Evaluation Modal Button </div>
                                </div>
                                <br/>
                                <div class="all-btn all-category d-flex align-items-center">
                                    <div class="btn btn-primary" @click="alert('살려주세요')"> Open Evaluation Modal Button </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Blog Post -->
                        
                    </div>
                    
                    <blogsidebar></blogsidebar>
                    
                </div>
            </div>
        </section>
        <!-- /Blog Details -->
        
      <layouts1></layouts1>
       
    </div>
    <!-- /Main Wrapper -->
</template>


<script>
export default {
    data() {
        return {
            evaluated_selected : false,
            evaluation_open : false,
            vote_open : false,

            // TODO : modal창 호출시 debateId를 통해 아래 데이터 받아오기 
            debateTitle : '반려동물 보유세 필요한가?',
            evaluation_list : [
                {name : '김토론', user_id :  'debate@naver.com'},
                {name : '김영희', user_id :  'young@naver.com'},
                {name : '김강산', user_id :  'mount@naver.com'},
            ],
            evaluation_category_list : [
                {id : 4, description : "자신의 주장에 대해 실질적인 자료와 데이터를 제시하였는가?"},
                {id : 5, description : "상대방의 의견을 비판할때 적절한 근거를 들어 명확하게 설명하였는가?"},
                {id : 6, description : "자신의 주장을 발표할 때 적절한 언어적 요소를 활용하였는가?"},
                {id : 7, description : "정해진 주제에 벗어난 발언을 하지 않았는가?"},
                {id : 8, description : "발언시간을 잘 관리하고 토론 규칙을 준수하여 발언하였는가?"},
            ],
            // TODO : 받아온 evaluation_list 유저 수만큼 boolean 배열 할당
            // 평가 하위 컴포넌트의 제출 여부에 따라 값 변경
            evaluation_status : [false, false, false],
        }
    },
    methods: {
        alert(str) {
            alert(str)
        },
        closeEvaluation(){
            this.evaluated_selected = false;
            this.evaluation_open = false;
        }
    }
}
</script>

<style>
.evaluationmodal {
  position: fixed;
  z-index: 999;
  top: 20%;
  left: 40%;
  width: 500px;
  margin-left: -150px;
  background-color:blanchedalmond;
}
.selectedmodal {
  position: fixed;
  z-index: 999;
  top: 50%;
  left: 40%;
  width: 500px;
  margin-left: -150px;
  background-color:blanchedalmond;
}
</style>
