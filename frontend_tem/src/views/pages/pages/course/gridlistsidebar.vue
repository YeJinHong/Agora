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
                            <div v-for="category in data.categories">
                                <label class="custom_check">
                                    <input type="checkbox" name="select_specialist" :value="category.id" @click="setSelectedCategories()">
                                    <span class="checkmark"></span> {{ category.codeName }}
                                </label>
                            </div>
                            
                        </div>
                    </div>
                </div>
                <!-- /Search Filter -->
            
            </div>
        </div>
    </div>
</template>

<script>    

import { apiInstance } from "/api/index.js";      
import {useStore} from "vuex";
import { onMounted, reactive,  watch} from "vue";

const api = apiInstance();

export default {
    setup(){
        const store = useStore();
        const data = reactive ({
            categories : [],
        })
        onMounted(() => {
            getCategories();
        })
        const getCategories = async ()=> {
            await store.dispatch("debate/getCategoryList");
        }
        
        watch (
            () => store.getters["debate/getCategoryList"],
            (val, oldVal) => {
			    data.categories = store.getters["debate/getCategoryList"];
		  }
        )

        const setSelectedCategories = ()=>{

            let check_val = [];
            $("input:checkbox[name=select_specialist]:checked").each(function(i, iVal){
                check_val.push(iVal.value);
            });
            console.log(check_val);

            // store.commit('debate/SET_SELECTED_CATEGORY_LIST', check_val);
            store.dispatch('debate/setSelectedCategoryList', check_val);
        }
        
      return {data, setSelectedCategories}
    },
}
</script>