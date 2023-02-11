<template>
  <div class="side-element">
    <div class="chatList" v-for="(m, idx) in chatList" :key="idx">
      <div class="chatItem msg-box">
        <div class="msg-bg">
          <strong class="name">{{ m.author }}</strong>
          <span class="comment">{{ m.content }}</span>
        </div>
        <ul class="chat-time" >
          <li style="list-style: none; float: right">{{ m.timestamp }}</li>
        </ul>
      </div>

    </div>
    <div id="sendBar">
      <input id="contentBar"
             placeholder="Send a message..."
             type="text"
             v-model="content"
             @keyup.enter="sendMessage"
      />
      <button id="sendButton" @click="sendMessage" name="전송" value="전송"/>
    </div>
  </div>
</template>

<script>
import {useStore} from 'vuex';

export default {
  name: "chat",
  setup() {
    const store = useStore();
    const socket = store.state.debate.socket
    const stompClient = store.state.debate.stompClient
    const chatList = store.state.debate.chatList
    return {store, socket, stompClient, chatList}
  },
  data() {
    return {
      content: '',
    }
  },
  methods: {
    sendMessage() {
      console.log('click send')
      console.log(this.content)
      console.log(this.content.trim())
      if (this.content.trim() !== '' && this.stompClient !== null) {
        let chatMessage = {
          'content': this.content,
          'roomId': 1,
          'author': '김피먹',
          'timestamp': new Date(Date.now()).toISOString().replace('T', ' ').replace(/\..*/, '')
        }
        this.stompClient.send("/pub/message", JSON.stringify(chatMessage), {})
        this.content = ''
      }
    }
  }
}
</script>

<style scoped>
.side-element {
  width: 400px;
  height: 100%;
  background-color: whitesmoke;
  padding: 10px;
}

.chatItem {
  padding: 2px 5px;
}

.name {
  padding: 0 5px;
}

.comment {
  padding: 0 5px;
}

#sendBar {
  width: 100%;
  position: absolute;
  bottom: 80px;
}

#contentBar {
  width: 80%;
}

#sendButton {
  width: 20%;
  height: 2px;
}

.msg-box > div {
  background-color: #FF875A;
  padding: 10px;
  border-radius: 10px;
  display: inline-block;
  margin-bottom: 5px;
}

.msg-box > div strong {
  color: #fff;
}

.msg-box > div span {
  color: #fff;
}

</style>