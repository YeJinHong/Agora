<template>
    <div id="container">
        <div id="wrapper">
            <div id="join" class="animate join">
                <h1>Join a Room</h1>
                <form  accept-charset="UTF-8">
                    <p>
                        <input type="text" name="name" v-model="name"
                            placeholder="Username" required>
                    </p>
                    <p>
                        <input type="text" name="room" v-model="room"
                            placeholder="Room" required>
                    </p>
                    <p class="submit">
                        <input type="button" @click="connect" name="commit" value="Join!">
                    </p>
                </form>
            </div>
            <div id="room" style="display: none;">
                <h2 id="room-header"></h2>
                <div id="participants"></div>
                <input type="button" id="button-leave"  @click="leaveRoom"
                    value="Leave room">
              <input type="button" id="button-mute"  @click="muteAudio"
                     value="Mute">
            </div>
        </div>
    </div>
</template>
<script>
import room from "../../../assets/js/conferenceroom.js";

import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  name : "debate",
  data() {
    return {
      name : "",
      room : "",
    }
  },
  mounted() {
    room.connect();
  },
  methods: {
    connect() {
      room.registerRoom(this.name, this.room);
    },
    leaveRoom() {
      room.leaveRoom();
    },
    muteAudio() {
      room.muteAudio(this.name)
    }
  }
}
</script>