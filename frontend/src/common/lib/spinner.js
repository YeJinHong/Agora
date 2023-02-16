import { ElLoading } from "element-plus";

// TODO : 회의 검색, 정보, 정렬, 방 상세정보, 지난 회의이력 클릭 시 응답 완료 시점까지 동작.
function openFullScreen() {
  const loading = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: 'rgba(0, 0, 0, 0.7)',
  })
  setTimeout(() => {
    loading.close()
  }, 500)
}

export default openFullScreen;
