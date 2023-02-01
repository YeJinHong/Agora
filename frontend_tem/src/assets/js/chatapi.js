import Axios from "axios";
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

const api = Axios.create({
    baseURL: "http://192.168.1.174:8080/chat",
});

const chatapi = {
    // getMessages: (groupId) => {
    //     console.log("Calling get messages from API");
    //     return api.get(`/messages/${groupId}`);
    // },

    sendMessage: (username, text, roomId) => {
        let msg = {
            author: username,
            content: text,
            roomId : roomId,
        };
        return api.post(`/publish`, msg, {
            headers: { "Content-Type": "application/json" },
        });
    },
};

export default chatapi;