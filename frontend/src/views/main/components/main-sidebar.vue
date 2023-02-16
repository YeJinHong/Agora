<template>
  <el-row
    class="main-sidebar"
    :gutter="10"
    :style="{ 'width': width }">
    <div class="hide-on-small">
      <el-menu
        :default-active="String(state.activeIndex)"
        active-text-color="#ffd04b"
        class="el-menu-vertical-demo"
        @select="menuSelect">
        <el-menu-item v-for="(item, index) in state.menuItems" :key="index" :index="index.toString()" @click="openFullScreen">
          <i v-if="item.icon" :class="['ic', item.icon]"/>
          <span>{{ item.title }}</span>
        </el-menu-item>
      </el-menu>
    </div>
  </el-row>
</template>
<style>
.main-sidebar .el-menu {
  margin-top: 0;
  padding-left: 0;
}
.main-sidebar .hide-on-small {
  height: 100%;
}
.main-sidebar .hide-on-small .el-menu {
  height: 100%;
}
.main-sidebar .el-menu .el-menu-item {
  cursor: pointer;
  border-right: none;
}
.main-sidebar .el-menu .el-menu-item .ic {
  margin-right: 5px;
}
</style>
<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import openFullScreen from "../../../common/lib/spinner";

export default {
  name: 'main-header',
  methods: { openFullScreen },

  props: {
    width: {
      type: String,
      default: '240px'
    }
  },
  setup() {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      searchValue: null,
      menuItems: computed(() => {
        const MenuItems = store.getters['menuStore/getMenus']
        let keys = Object.keys(MenuItems)
        let menuArray = []
        for (let i = 0; i < keys.length; ++i) {
          let menuObject = {}
          menuObject.icon = MenuItems[keys[i]].icon
          menuObject.title = MenuItems[keys[i]].name
          menuArray.push(menuObject)
        }
        console.log(menuArray)
        return menuArray
      }),
      activeIndex: computed(() => store.getters['menuStore/getActiveMenuIndex'])
    })

    if (state.activeIndex === -1) {
      state.activeIndex = 0
      store.commit('menuStore/setMenuActive', 0)
    }

    const menuSelect = function (param) {
      store.commit('menuStore/setMenuActive', param)
      const MenuItems = store.getters['menuStore/getMenus']
      let keys = Object.keys(MenuItems)
      router.push({
        name: keys[param]
      })
    }


    // TODO : home.vue에서 데이터 로딩시까지로 변경, 각 화면에 적용(검색, 정렬, 방 상세). 공통 기능으로 코드 위치 변경.
    // const openFullScreen = () => {
    //   const loading = ElLoading.service({
    //     lock: true,
    //     text: 'Loading',
    //     background: 'rgba(0, 0, 0, 0.7)',
    //   })
    //   setTimeout(() => {
    //     loading.close()
    //   }, 500)
    // }

    return { state, menuSelect}
  }
}
</script>
