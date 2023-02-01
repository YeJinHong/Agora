export default {

    install(Vue) {

        // [getSum 함수 설정]
        Vue.config.globalProperties.$getSum = function(one, two){
            return one + two;
        },

        // [로컬 스토리지 데이터 저장]
        Vue.config.globalProperties.$setLocalStorage = function(key, value){
            localStorage.setItem(key, value);
        },


        // [로컬 스토리지 저장 값 호출]
        Vue.config.globalProperties.$getLocalStorage = function(key){
            return localStorage.getItem(key);
        },


        // [로컬 스토리지 특정 값 삭제]
        Vue.config.globalProperties.$delItemLocalStorage = function(key){
            localStorage.removeItem(key);
        },


        // [로컬 스토리지 전체 데이터 삭제]
        Vue.config.globalProperties.$delAllLocalStorage = function(){
            localStorage.clear();
        }
    }
}


// [로컬 스토리지 저장, 호출, 삭제 - 소스코드] 코드 쓸때 참고하세요
// // [로컬 스토리지 데이터 저장 실시]
// this.$setLocalStoage("NAME", "TWOK");
//
// // [로컬 스토리지 데이터 호출 실시]
// this.$getLocalStoage("NAME");
//
// // [로컬 스토리지 데이터 삭제 실시]
// this.$delItemLocalStoage("NAME");