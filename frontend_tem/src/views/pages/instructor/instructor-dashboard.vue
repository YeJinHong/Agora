<template>
    <!-- Main Wrapper -->
    <div class="main-wrapper">

      <layouts></layouts>
       
        <!-- Page Wrapper -->
			<div class="page-content instructor-page-content">
				<div class="container">
					<div class="row">

            <studentsidebar></studentsidebar>
						
						<!-- Instructor Dashboard -->
						<div class="col-xl-9 col-lg-8 col-md-12">	

							<div class="row">

							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="card instructor-card">
										<div class="card-header">
											<h4>토론 역활 비율 </h4>
										</div>
										<div class="card-body">
                      <apexchart :options="option" :series="series" type="pie" height="350" ></apexchart>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="settings-widget">
										<div class="settings-inner-blk p-0">
											<div class="sell-course-head comman-space">
                        <div style="text-align: start">
                          <h3>이전 참여 목록</h3>
                        </div>
                        <div style="text-align: end">
                          <button class="btn btn-primary" @click="certification">증명서 발급하기</button>

                        </div>
                      </div>
                      <div class="comman-space pb-0">
												<div class="settings-tickets-blk course-instruct-blk table-responsive">

													<!-- Referred Users-->
													<table class="table table-nowrap mb-0">
														<thead>
															<tr>
																<th>날짜</th>
																<th>주제</th>
																<th>역활</th>
                                <th>소요 시간</th>
															</tr>
														</thead>
														<tbody v-for="item in history">
															<tr>
																<td>{{item.date}}</td>
																<td>{{ item.title }}</td>
																<td>{{item.role}}</td>
                                <td>{{item.activeTime}}</td>
                              </tr>
                            </tbody>
													  </table>
													<!-- /Referred Users-->	

												</div>									
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>	
						<!-- /Instructor Dashboard -->
						
					</div>
				</div>
			</div>	
			<!-- /Page Wrapper -->
        
        <layouts1></layouts1>
       
    </div>
    <!-- /Main Wrapper -->
</template>
<script>

// import { ref } from "vue";
// import ApexCharts from 'apexcharts'
// import VueApexCharts from "vue3-apexcharts";
// import {apiInstance} from "../../../api";
// export default {
//
//   data() {
//     return {
//       chartOptions: {
//         chart: {
//           type: "line"
//         },
//         xaxis: {
//           type: "datetime"
//         },
//         yaxis: {
//           title: {
//             text: "Content"
//           }
//         },
// 	  colors: ['#FF9364'],
// 	  markers: {
// 			size: 3,
// 		},
//         dataLabels: {
//           enabled: false
//         },
//         stroke: {
//           curve: 'smooth',
// 		  width: 3,
//         },
// 		legend: {
// 			position: 'top',
// 			horizontalAlign: 'right',
// 		 },
//         grid: {
//           show: false,
//         },
//       },
// 	  series: [{
// 	name: "Current month",
// 	data: [0, 10, 40, 43, 40, 25, 35, 25, 40, 30],
// }],
//     };
//   },
// mounted(){
//     const api = apiInstance();
//        api.get("/userDebates")
//            .then(response => {
//              this.series = [
//                {
//                  name: "Data",
//                  data: response.data.map(data => [data.time, data.content])
//                }
//              ];
//            })
//            .catch(error => {
//              console.error(error);
//            });
//      }
//   components: {
//     apexchart: VueApexCharts
//   },
//   data() {
//     return {
//       series: [],
//       chartOptions: {
//         chart: {
//           type: "line"
//         },
//         xaxis: {
//           type: "datetime"
//         },
//         yaxis: {
//           title: {
//             text: "Content"
//           }
//         }
//       }
//     };
//   },
//   mounted() {
//     const api = apiInstance();
//         .then(response => {
//           console.log(response);
//           console.log(response.data.data.content.map(data => ["2022.12.20", data.title]))
//           if(data.data.content != null) {
//             this.series = [
//               {
//                 name: "Data",
//                 data: response.data.data.content.map(data => ["2022.12.20", data.title])
//               }
//             ];
//           }
//         })
//         .catch(error => {
//           if(error.response.status == 401){
//             alert("로그인이 필요합니다.")
//             route.push("/login")
//           }
//         });
//   }
// };

import {apiInstance} from "../../../api";
import { ref, onMounted } from 'vue';
import VueApexCharts  from 'vue3-apexcharts';

export default {
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      file : null,
      fileData : null,
      history:{},
      option :{
        chart: {
          width: 380,
          type: 'pie',
        },
        labels: []
      },
      series: [],
    };
  },
  methods: {
    async certification(){
      const api = apiInstance();
     try{
       api.defaults.headers["authorization"] = "Bearer " +  sessionStorage.getItem("access-token");
      const response =  await api.get('/certification');
      this.fileData = response.data;
       const file = new Blob([this.fileData], {type: 'text/plain'});
       const fileURL = URL.createObjectURL(file);
       const link = document.createElement('a');
       link.href = fileURL;
       link.setAttribute('download', 'file.txt');
       document.body.appendChild(link);
       link.click();
       link.remove();
     } catch (error) {
       console.error(error);
     }
    }
  },
  async mounted() {
    const api = apiInstance();
    api.defaults.headers["authorization"] = "Bearer " +  sessionStorage.getItem("access-token");
    const response =  await api.get('/userDebates');
    this.history = response;
    const data = response.data.histories;
    console.log(data)
    let roles = {};
    for (const item of data) {
      if (!roles[item.role]) {
        roles[item.role] = 1;
      } else {
        roles[item.role] += 1;
      }
    }
    for(const text of Object.keys(roles)){
      this.option.labels.push(text);
    }
    this.option.labels = Object.keys(roles);
    this.series = Object.values(roles);
    this.history = response.data.histories;
  }

};
</script>