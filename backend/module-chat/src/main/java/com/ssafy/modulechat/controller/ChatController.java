package com.ssafy.modulechat.controller;


import com.ssafy.modulechat.model.KafkaConstants;
import com.ssafy.modulechat.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @PostMapping(value = "/publish")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        log.info("Produce message : " + message.toString());
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @MessageMapping("/sendMessage")
//    @SendTo("/topic/group")
//    public Message broadcastGroupMessage(@Payload Message message) {
//        return message;
//    }

}