<template>
  <div class="form-group">
    <label for="thumbnail">토론 썸네일</label>
    <div>
      <input type="file" @change="uploadFile" id="thumbnail" class="btn btn-outline-dark">
    </div>
    <div>
      <div v-for="file in state.debate.fileList">
        <a :href="file.fileDownloadUri">{{ file.fileName }}</a>
      </div>
    </div>
  </div>
</template>

<script>
import {useStore} from 'vuex';
import {onMounted, reactive} from "vue";
import {apiInstance} from "../../../api/index";

export default {
  name: "document",
  setup() {
    const store = useStore();
    const api = apiInstance();
    api.defaults.headers["authorization"] = "Bearer " + sessionStorage.getItem("access-token");
    const state = reactive({
      debate: {
        id: 15,
        userEmail: "",
        role: "찬성",
        fileList: null,
      }
    });

    onMounted(() => {
      state.debate.id = store.getters["debate/getDebateId"];
      state.debate.userEmail = store.getters["userStore/checkUserInfo"].userEmail;
      // state.debate.role = store.getters.["debate/getParticipantInfo"].position;
      console.log(state.debate.id);
    })

    const uploadFile = (event) => {
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append('role', state.debate.role);
      formData.append('file', file);
      api.patch('/debates/files/' + state.debate.id, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then((data) => {
        console.log(data);
        state.debate.fileList = data.data;
      }).catch((error) => {
        console.log(error);
      })
    }
    return {store, state, uploadFile}
  },
  data() {
    return {}
  },
}
</script>

<style scoped>
.document {
  /*background: cornflowerblue;*/
  opacity: 0.8;
  border-radius: 0.1%;
  /*height: ;*/
}

</style>