<template>
  <div class="main-wrapper">
    <layoutsinstructor></layoutsinstructor>
    <div class="page-content">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="filter-grp ticket-grp tiket-suport d-flex align-items-center justify-content-between">
              <div>
                <h3>토론 생성</h3>
              </div>
              <div class="ticket-btn-grp">
                <router-link to="index">Back to Home</router-link>
              </div>
            </div>
          </div>
          <div class="settings-widget">
            <div class="settings-inner-blk add-course-info new-ticket-blk p-0">
              <div class="comman-space">
                <h4>토론 설정</h4>
                <div>
                  <div class="form-group">
                    <label for="title" class="form-control-label">토론 제목</label>
                    <input type="text" class="form-control" v-model="state.debate.title">
                  </div>
                  <div class="form-group">
                    <label class="add-course-label">토론 설명</label>
                    <textarea
                        v-model="state.debate.description"
                    />
                  </div>
                  <div class="form-group">
                    <label class="form-label">토론 카테고리</label>
                    <div>
                      <select class="form-control" v-model="state.debate.category" name="sellist1">
                        <option value="">Choose Category</option>
                        <option
                            v-for="(item, index) in state.option.categories"
                            :key="index"
                            :value="item.id"
                        >{{ item.codeName }}
                        </option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="form-label">토론 모드</label>
                    <vue-select :options="state.option.modes" placeholder="Choose Category" name="sellist1"/>
                  </div>
                  <div class="form-group">
                    <label class="form-label">사회자 여부</label>
                    <div>
                      <input type="radio" name="select_specialist" v-model="state.debate.moderateOnOff" value="1">
                      <span class="checkmark"></span> 사회자 참여
                      <input type="radio" name="select_specialist" v-model="state.debate.moderateOnOff" value="2">
                      <span class="checkmark"></span> 사회자 불참
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="start-time">토론 시작 시간</label>
                    <div>
                      <input id="start-time" type="datetime-local" v-model="state.debate.callStartTime"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="end-time">토론 종료 시간</label>
                    <div>
                      <input id="end-time" type="datetime-local" v-model="state.debate.callEndTime"/>
                    </div>
                  </div>
                </div>
                <div class="submit-ticket">
                  <button type="button" class="btn btn-primary" @click.prevent="">생성</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {onMounted, reactive} from "vue";
import {useRouter, useRoute} from 'vue-router';
import {useStore} from 'vuex';
import SummernoteEditor from 'vue3-summernote-editor';
import axios from "axios";

export default {
  name: 'debateConfiguration',
  components: {SummernoteEditor},
  setup() {
    const router = useRouter();
    const store = useStore();
    axios.defaults.baseURL = "http://localhost:8082/api/v1";
    const state = reactive({
      debate: {
        title: '',
        description: '',
        category: '',
        mode: '',
        moderateOnOff: '',
        callStartTime: '',
        callEndTime: '',
      },
      option: {
        categories: null,
        modes: ['CEDA', '시간총량제'],
      }
    });

    onMounted(() => {
      axios.get(`codes/category`)
          .then((data) => {
            let result = data["data"].data;
            console.log(result);
            state.option.categories = result;
          })
          .catch((error) => {
            alert("error : " + error.code);
          })
    })

    const saveDebateConfig = () => {
      const req = {
        ownerId: store.state.userStore.userInfo.userEmail,
        category: state.debate.category,


      }

      http.post('/debates', {})
    }

    return {state, saveDebateConfig}
  },
  // methods: {
  //   // saveDebateConfig() {
  //   //   // Implement logic to save the debate configuration
  //   //   console.log('Debate Configuration:', this.debateTitle, this.debateDescription, this.debateState, this.debateCategory, this.debateMode, this.debateOption);
  //   // }
  // }
};
</script>


<style scoped>

</style>