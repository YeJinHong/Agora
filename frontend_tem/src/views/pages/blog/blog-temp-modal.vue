<template>
    <!-- Deposit Modal -->
    <div class="modal-styles modal fade" id="depositMethod" tabindex="-1" aria-labelledby="addpaymentMethod" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                <h5 class="modal-title" id="addpaymentMethod"> 상호 평가 </h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><i class="fa-regular fa-circle-xmark"></i></button>
                </div>
                <div class="modal-body">
                    <div class="addpaymethod-form add-course-info">
                        <form action="#">
                            <div class="row">
                                <div class="col-lg-12">
                                
                                    <!-- Deposit Method -->
                                    <div class="radio-with-img">
                                        <p class="radio-deposit-item">
                                            <input type="radio" name="deposittypes" id="deposit-type-two" value="false" class="ng-valid ng-dirty ng-touched ng-empty" aria-invalid="false"  checked>
                                            <label for="deposit-type-two">
                                              <img src="../../../assets/img/deposit-02.jpg" alt="" class="img-fluid" >
                                              상대팀2
                                            </label>
                                        </p>
                                        <p class="radio-deposit-item">
                                            <input type="radio" name="deposittypes" id="deposit-type-one" value="true" class="ng-valid ng-dirty ng-touched ng-empty" aria-invalid="false"  >
                                            <label for="deposit-type-one">
                                              <img src="../../../assets/img/deposit-01.jpg" alt="" class="img-fluid" >
                                              상대팀 1
                                            </label>
                                        </p>
                                        <p class="radio-deposit-item">
                                            <input type="radio" name="deposittypes" id="deposit-type-three" value="false" class="ng-valid ng-dirty ng-touched ng-empty" aria-invalid="false" >
                                            <label for="deposit-type-three">
                                              <img src="../../../assets/img/deposit-03.jpg" alt="" class="img-fluid" >
                                              상대팀 3
                                            </label>
                                        </p>
                                        <p class="radio-deposit-item me-0">
                                            <input type="radio" name="deposittypes" id="deposit-type-four" value="false" class="ng-valid ng-dirty ng-touched ng-empty" aria-invalid="false" >
                                            <label for="deposit-type-four">
                                              <img src="../../../assets/img/deposit-04.jpg" alt="" class="img-fluid" >
                                              상대팀 4
                                            </label>
                                        </p>
                                    </div>
                                    <!-- /Deposit Method -->
                                    
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group mb-0">
                                        <label class="form-control-label">Enter Amount:</label>
                                        <input type="text" class="form-control" placeholder="Amount">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer me-auto">
                    <button type="button" class="btn btn-modal-style btn-theme">Submit</button>
                    <button type="button" class="btn btn-modal-style btn-cancel" data-bs-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>	   
    <!-- Deposit Modal -->
</template>

<script>
import { ref } from "vue";
import { useStore } from "vuex";

export default {
    setup(){
        // TODO : debateStore에서 가져와야한다. 이 참여자 정보는 토론 방 입장 시 가져온다.
        // GET /debate/{:debateId}
        // mutation - debateStore에서 나의 팀정보(perspective_id)와 다른 나머지 팀의 정보만을 받아온다.
        const debateInfo = {
            debate_id : "2",
            debate_title : "려동물 보유세 필요한가?",
            pannel : [
                {
                    perspective_id : 1,
                    prespective_name : "반려동물 보유세는 필요하다.",
                    memberList : [
                        {user_email : "ssafy@naver.com", userName : "김싸피"},
                        {user_email : "human@naver.com", userName : "박사람"},
                        {user_email : "puppy@naver.com", userName : "김뽀삐"},
                    ]
                },
                {
                    perspective_id : 2,
                    prespective_name : "반려동물 보유세는 필요하지 않다.",
                    memberList : [
                        {user_email : "debate@naver.com", userName : "김토론"},
                        {user_email : "news@naver.com", userName : "신 문"},
                        {user_email : "student@naver.com", userName : "나학생"},
                    ]
                },
            ],
        };
        // GET /evaluations/questions
        const question_list = [
                {parent_id : 4, id : 10, question : "자신의 주장에 대해 실질적인 자료와 데이터를 제시하였는가?"},
                {parent_id : 5, id : 11, question : "상대방의 의견을 비판할때 적절한 근거를 들어 명확하게 설명하였는가?"},
                {parent_id : 6, id : 12, question : "자신의 주장을 발표할 때 적절한 언어적 요소를 활용하였는가?"},
                {parent_id : 7, id : 13, question : "자신의 주장을 발표할 때 적절한 비언어적 요소를 활용하였는가?"},
                {parent_id : 8, id : 14, question : "정해진 주제에 벗어난 발언을 하지 않았는가?"},
                {parent_id : 9, id : 15, question : "발언시간을 잘 관리하고 토론 규칙을 준수하여 발언하였는가?"},
        ];
        const evaluation_data = {
            debate_id : ref(this.debateInfo.debate_id),
            evaluated_id : ref(0),
            content : ref([]),
        };
    }
}
</script>

<style>
body {
    margin : 0
}
div {
    box-sizing : border-box;
}
.black-bg {
    width: 100%; height: 100%;
    background: rgba(0,0,0,0.5);
    position: fixed; padding: 20px;
}
.white-bg {
    width : 100%; background: white;
    border-radius: 8px;
    padding:20px;
    margin-top: 200px;
}
</style>