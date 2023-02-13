<template>
<!-- Blog pagination -->
<div class="row">
    <div class="col-md-12">
        <ul class="pagination lms-page">
            <li class="page-item prev">
                <a class="page-link" href="javascript:void(0);" tabindex="-1"><i class="fas fa-angle-left"></i></a>
            </li>
            <li class="page-item first-page active" v-if="data.totalPages==0" id="page-item-1">
                <a class="page-link" href="javascript:void(0);">1</a>
            </li>
            <li class="page-item" v-for="(page, index) in data.totalPages" :id="'page-item-'+(index+1)" @click ="loadDebateList">
                <a class="page-link" href="javascript:void(0);">{{ index+1 }}</a>
            </li>
            <li class="page-item next">
                <a class="page-link" href="javascript:void(0);"><i class="fas fa-angle-right"></i></a>
            </li>
        </ul>
    </div>
</div>
<!-- /Blog pagination -->
</template>
<script>
  import {onMounted, reactive} from 'vue'
  import {useStore} from "vuex";
  
  export default {

    setup() {
      const store = useStore();
      const data = reactive({
        selectedPageIndex : store.state.debate.selectedPageIndex,
        totalPages : store.state.debate.totalElements,
        totalElements : store.state.debate.totalElements,
        pageNumber : store.state.debate.pageNumber, 
        size : store.state.debate.size,
        numberOfElements : store.state.debate.numberOfElements,
      })

      // TODO : 테스트용 데이터 지우기
       //   data.totalPages = 5;
     //   data.selectedPageIndex = 2;
      onMounted(() => {
        document.getElementById('page-item-'+data.selectedPageIndex).className = 'page-item active';
      })

      const loadDebateList = async () => {
        await store.dispatch("debate/searchDebateList", {
          condition: data.selectedOptionName,
          keyword: data.keyword,
          page : data.pageNumber,
        })
      }
      return {data, loadDebateList};
    },
  
    computed: {

    },
    data() {

    },
    mounted() {
  
    }
  }

  </script>
  