import Stomp from 'webstomp-client'
import SockJsClient from 'sockjs-client'
import Axios from "axios"

function Chat({ messages ,currentUser}) {

    const formattingTimestamp = (timestamp) => {
        const date = new Date(timestamp);
        let hour = date.getHours() < 10 ? `0${date.getHours()}` : date.getHours();
        let min =
            date.getMinutes() < 10 ? `0${date.getMinutes()}` : date.getMinutes();
        return `${hour}:${min}`;
    };

    return (
        <div className="chat-middle">
            {messages.map((msg) => (
                <li className={`${
                    msg.author === currentUser.name ? "sent" : "received"
                }`}>
                    <div className="media-body">
                        <div className="msg-box">
                            <div><p>{msg.content}</p>
                                <ul className="chat-msg-info">
                                    <li>
                                        <div className="chat-time"><span>{formattingTimestamp(msg.timestamp)}</span></div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </li>
            ))}
        </div>
    );
}

function send() {
    const [messages, setMessages] = useState([]);
    const [user, setUser] = useState(null);

    const onMessageReceived = (msg) => {
        console.log("New Message Received!!", msg);
        setMessages(messages.concat(msg));
    };

    const handleLoginSubmit = (name) => {
        setUser({ name: name, color: randomColor() });
    };

    const handleMessageSubmit = (msg) => {
        chatApi
            .sendMessage(user.name, msg)
            .then((res) => {
                console.log("sent", res);
            })
            .catch((e) => {
                console.log(e);
            });
    };

    const randomColor = () => {
        return "#" + Math.floor(Math.random() * 0xffffff).toString(16);
    };

    return (
        <>
            {user !== null ? (
                <div className="chat-container">
                    <SockJsClient
                        url={"http://192.168.1.174:6002/my-chat/"}
                        topics={["/topic/group"]}
                        onConnect={console.log("connected!")}
                        onDisconnect={console.log("disconnected!")}
                        onMessage={(msg) => onMessageReceived(msg)}
                        debug={false}
                    />
                    <Chat messages={messages} currentUser={user} />
                    <Input handleOnSubmit={handleMessageSubmit} />
                </div>
            ) : (
                <Login handleOnSubmit={handleLoginSubmit} />
            )}
        </>
    );
}

export default Chat;