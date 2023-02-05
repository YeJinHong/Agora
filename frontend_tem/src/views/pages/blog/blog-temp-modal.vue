<template>
    <!-- Deposit Modal -->
    <div class="modal-styles modal fade" id="depositMethod" tabindex="-1" aria-labelledby="addpaymentMethod" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                <h5 class="modal-title" id="addpaymentMethod"> {{ this.debate_info.debate_title }} 토론 상호평가</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><i class="fa-regular fa-circle-xmark"></i></button>
                </div>
                <div class="modal-body">
                    <div class="addpaymethod-form add-course-info">
                        <form action="#" id="evaluation_modal_form"  name = "content">
                            <div class="row">
                                <div class="col-lg-12">
                                
                                    <!-- Deposit Method -->
                                    <div class="radio-with-img">
                                        <p class="radio-deposit-item">
                                            <input type="radio" name="evaluated_id" id="deposit-type-one" :value="this.debate_info.pannel[0].memberList[0].user_email" class="ng-valid ng-dirty ng-touched ng-empty" aria-invalid="false" v-model="evaluated_id">
                                            <label for="deposit-type-one">
                                              <img src="../../../assets/img/deposit-01.jpg" alt="" class="img-fluid" >
                                              {{ this.debate_info.pannel[0].memberList[0].user_name }}
                                            </label>
                                        </p>
                                        <p class="radio-deposit-item">
                                            <input type="radio" name="evaluated_id" id="deposit-type-two" :value="this.debate_info.pannel[0].memberList[1].user_email" class="ng-valid ng-dirty ng-touched ng-empty" aria-invalid="false" v-model="evaluated_id">
                                            <label for="deposit-type-two">
                                              <img src="../../../assets/img/deposit-02.jpg" alt="" class="img-fluid" >
                                              {{ this.debate_info.pannel[0].memberList[1].user_name }}
                                            </label>
                                        </p>
                                        <p class="radio-deposit-item">
                                            <input type="radio" name="evaluated_id" id="deposit-type-three" :value="this.debate_info.pannel[0].memberList[2].user_email" class="ng-valid ng-dirty ng-touched ng-empty" aria-invalid="false" v-model="evaluated_id">
                                            <label for="deposit-type-three">
                                              <img src="../../../assets/img/deposit-03.jpg" alt="" class="img-fluid" >
                                              {{ this.debate_info.pannel[0].memberList[2].user_name }}
                                            </label>
                                        </p>
                                    </div>
                                    <!-- /Deposit Method -->
                                    
                                    <div class="form-group mb-0">
                                        <input type="hidden" name = "debate_id" :value="this.debate_info.debate_id">
                                        <!-- <input type="hidden" name = "evaluated_id" :value="this.evaluated_id"> -->
                                        
                                        <div class="from-group col-md-10" v-for="(question, index) in question_list" :key="index">
                                            <input type="hidden" name = "parent_id" :value="question.parent_id" >
                                            <input type="hidden" name = "id" :value="question.id" >
                                            <label class="form-control-label" >{{ question.question }}</label> 
                                            <div class="col-md-6 col-lg-6 col-item">
                                                <vue-select :options="point" placeholder="Choose" name = "point"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer me-auto">
                    <!-- <button type="button" class="btn btn-modal-style btn-theme" @click ="handleClick()" >Submit</button> -->
                    <button type="button" class="btn btn-modal-style btn-theme" @click ="handleClick()" >Submit</button>
                    <button type="button" class="btn btn-modal-style btn-cancel" data-bs-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>	   
    <!-- Deposit Modal -->
</template>

<script>    

import { apiInstance } from "/api/index.js";      

export default {
    setup(){
        // TODO : debateStore에서 현재 토론 정보 받아오기. 토론 참여시 GET 요청으로 받아와 state로 저장되어 있어야한다.
        const debate_info = {
            debate_id : "1",
            debate_title : "반려동물 보유세 필요한가?",
            pannel : [
                {
                    perspective_id : 1,
                    prespective_name : "반려동물 보유세는 필요하다.",
                    memberList : [
                        {user_email : "agree1@naver.com", user_name : "김찬성1"},
                        {user_email : "agree2@naver.com", user_name : "김찬성2"},
                        {user_email : "agree3@naver.com", user_name : "김찬성3"},
                    ]
                },
                {
                    perspective_id : 2,
                    prespective_name : "반려동물 보유세는 필요하지 않다.",
                    memberList : [
                        {user_email : "ssafy@naver.com", user_name : "김싸피"},
                        {user_email : "human@naver.com", user_name : "박사람"},
                        {user_email : "puppy@naver.com", user_name : "김뽀삐"},
                    ]
                },
            ],
        };
        // 참여자의 역할에 따라 다른 문항 제공. 현재는 모든 사람이 동일하다. GET /evaluations/questions
        const question_list = [
                {parent_id : 4, id : 10, question : "자신의 주장에 대해 실질적인 자료와 데이터를 제시하였는가?"},
                {parent_id : 5, id : 11, question : "상대방의 의견을 비판할때 적절한 근거를 들어 명확하게 설명하였는가?"},
                {parent_id : 6, id : 12, question : "자신의 주장을 발표할 때 적절한 언어적 요소를 활용하였는가?"},
                {parent_id : 7, id : 13, question : "자신의 주장을 발표할 때 적절한 비언어적 요소를 활용하였는가?"},
                {parent_id : 8, id : 14, question : "정해진 주제에 벗어난 발언을 하지 않았는가?"},
                {parent_id : 9, id : 15, question : "발언시간을 잘 관리하고 토론 규칙을 준수하여 발언하였는가?"},
        ];


        return {debate_info, question_list};
    },
    data(){
        return {
            point : [1, 2, 3, 4, 5],
            evaluated_id : '',
        }
    },
    methods: {
        async handleClick () {
            let form = {
                debate_id : '',
                evaluated_id : '',
                content : [
                ],
            };

            let formData = new FormData($("#evaluation_modal_form")[0]);
            // console.log(formData.getAll('parent_id'));
            // console.log(formData.getAll('debate_id'));
            console.log(formData.getAll('evaluated_id'));
            console.log(this.evaluated_id);
            // console.log(formData.get('evaluated_id'));
            // console.log(formData.getAll('id'));
            // console.log(formData.getAll('point'));

            // formData 보내는 방법(그대로, serialize, stringify 등)이 안통함. 기존의 객체 양식과 전송시 형태가 다름. 아래는 임시 방편.
            form.debate_id = this.debate_info.debate_id;
            form.evaluated_id = this.evaluated_id;
            let parent_id_list = formData.getAll('parent_id');
            let id_list = formData.getAll('id');
            let point_list = formData.getAll('point');
            if(this.evaluated_id == ''){
                alert('평가 대상을 선택해주세요');
                return;
            } else if(parent_id_list.length != point_list.length){
                alert('모든 평가 점수를 입력해야합니다.')
                return;
            }
            for(var i = 0; i < parent_id_list.length; i++){
                let parent_id = parent_id_list[i];
                let id = id_list[i];
                let point = point_list[i];
                form.content[i] = {parent_id, id, point};
            }
            console.log(form);


            const api = apiInstance();

            api.defaults.headers["Authorization"] = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FmeUBuYXZlci5jb20iLCJpc3MiOiJSZXNldENvbnRlbnQiLCJleHAiOjE2NzU2MjYwODMsImlhdCI6MTY3NTYyNDI4M30.TY8PKS99W_9Qw6QUKiUmVEFRTsJGffK5ZhhhhgWTGA5fDO0EfH2JAZ2ONNV2uzvSS1a8UZBkq5MSWWnXzOQ9kQ";
            await api.post(`/evaluations`, form)
            .then((response) => {
                console.log(response);
                alert("유저 평가 생성 완료");
                $('input[name="evaluated_id"]:checked').attr("disabled"); // 안통함.
                // $('input[name="evaluated_id"]:checked').removeAttr("checked");  // 전송이 끝나면 자동으로 unchecked가 된다.
                this.evaluated_id = '';
            })
            .catch((error)=>{
                console.log(error);
            });
        }
    },
    
}
</script>