<template>
<!-- Blog pagination -->
<div class="row">
    <div class="col-md-12">
        <ul class="pagination lms-page">
            <li class="page-item prev">
                <a class="page-link" href="javascript:void(0);" tabindex="-1"><i class="fas fa-angle-left"></i></a>
            </li>
            <li class="page-item first-page active" v-if="store.state.debate.totalPages==0" id="page-item-1">
                <a class="page-link" href="javascript:void(0);">1</a>
            </li>
            <li class="page-item" v-for="(page, index) in store.state.debate.totalPages" :id="'page-item-'+(index+1)" @click ="loadDebateList(index+1)">
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
  import {onMounted, reactive, watch} from 'vue'
  import {useStore} from "vuex";
  
  export default {

    setup() {
      const store = useStore();
      const data = reactive({
        selectedPageIndex : store.state.debate.selectedPageIndex,
        totalPages : store.state.debate.totalPages,
        totalElements : store.state.debate.totalElements,
        pageNumber : store.state.debate.pageNumber, 
        numberOfElements : store.state.debate.numberOfElements,
      })

      onMounted(() => {
        document.getElementById('page-item-'+data.selectedPageIndex).className = 'page-item active';
      })

      watch(
		  // pretend you have a getData getter in store
		  () => store.getters["debate/getPageNumber"],
		  (val, oldVal) => {
			console.log("val : "+val);
			console.log("oldVal : "+oldVal);
		  }
	  )

      const loadDebateList = async (num) => {
        for(var i = 1 ; i <= store.state.debate.totalPages; i++){
            document.getElementById('page-item-'+i).className = 'page-item';
        }
        document.getElementById('page-item-'+num).className = 'page-item active';
        store.commit('debate/SET_SELECTED_PAGE_INDEX', num);
        await store.dispatch("debate/searchDebateList", {page : num-1})
      }
      return {data, loadDebateList, store};
    },
  
    computed: {

    },
    data() {

    },
    mounted() {
  
    }
  }

  </script>
  