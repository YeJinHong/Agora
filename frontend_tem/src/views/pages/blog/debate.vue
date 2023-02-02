<template>
  <div class="chat-cont-right">
    <div class="chat-body">
      <div class="chat-scroll">
        <ul class="list-unstyled">

<!--          <li class="media received">-->
<!--            <div class="avatar"><img src="./src/assets/img/icon/smile.svg"-->
<!--                                     class="avatar-img rounded-circle"></div>-->
<!--            <div class="media-body">-->
<!--              <div class="msg-box">-->
<!--                <div>{{recvList}}-->
<!--                  <ul class="chat-msg-info">-->
<!--                    <li>-->
<!--                      <div class="chat-time"><span>{{recvList}}</span></div>-->
<!--                    </li>-->
<!--                  </ul>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </li>-->


<!--                    <div v-html="recvList"></div>-->

                    {{recvList}}

        </ul>

      </div>
    </div>
    <div class="chat-footer">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="btn-file btn mt-3"><i class="fa fa-paperclip"></i><input type="file"></div>
        </div>
        <input type="text" class="input-msg-send form-control" placeholder="Type something" v-model="message">
        <div class="input-group-append">
          <button type="button" class="btn msg-send-btn" @click="sendMessage()"><i class="fab fa-telegram-plane"
                                                                                   @click="sendMessage()"></i></button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import Axios from "axios"
import chatapi from "Assets/js/chatapi";

const api = Axios.create({
  baseURL: "http://192.168.1.174:6002/kafka",
});

export default {
  name: "debate",
  data() {
    return {
      username: "문태호",
      message: "",
      roomId: 2,
      recvList : []
    }
  },
  watch: {
    recvList(recvList) {
      console.log("message " + this.recvList)
    }
  },
  created() {
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect()
  },
  methods: {
    sendMessage() {
      console.log("message")
      if (this.username !== '' && this.message !== '') {
        console.log("Send message:" + this.message);
        if (this.stompClient && this.stompClient.connected) {
          chatapi.sendMessage("username", this.message, this.roomId)
        }
        this.message = ''
      }
    },

    translate(msg) {
      if(msg.author === this.username){

      }
      // this.recvList +=
      //     ' <li class="media '
      // if(this.username === msg.author){
      //   this.recvList += 'sent'
      // }else {
      //   this.recvList += 'received'
      // }
      // this.recvList += '> <div class="media-body"> <div class="msg-box"> <div>' +
      //     msg.content + '<ul class="chat-msg-info"> <li> <div class="chat-time"><span>' +
      //     msg.timestamp + '</span> </div> </li> </ul> </div> </div> </div> </li>'

      console.log('test' + this.recvList)

    },
    connect() {
      const serverURL = "http://192.168.1.174:8080/my-chat"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
          {},
          frame => {
            // 소켓 연결 성공
            this.connected = true;
            console.log('소켓 연결 성공', frame);
            // 서버의 메시지 전송 endpoint를 구독합니다.
            // 이런형태를 pub sub 구조라고 합니다.
            this.stompClient.subscribe("/topic/group/2", res => {
              console.log('구독으로 받은 메시지 입니다.', res.body);

              // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
              this.recvList += this.translate(JSON.parse(res.body))
            });
          },
          error => {
            // 소켓 연결 실패
            console.log('소켓 연결 실패', error);
            this.connected = false;
          }
      );
    }
  }
}
</script>

<style scoped>

</style>


<!--<div className="chat-middle">-->
<!--<li className={`${-->
<!--    msg.author === currentUser.name ? "sent" : "received" }`}>-->
<!--<div className="media-body">-->
<!--  <div className="msg-box">-->
<!--    <div><p>{msg.content}</p>-->
<!--      <ul className="chat-msg-info">-->
<!--        <li>-->
<!--          <div className="chat-time"><span>{formattingTimestamp(msg.timestamp)}</span></div>-->
<!--        </li>-->
<!--      </ul>-->
<!--    </div>-->
<!--  </div>-->
<!--</div>-->
<!--</li>-->
<!--</div>-->
