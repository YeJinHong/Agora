package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class ModuleKurentoApplication {

    public static void main(String[] args) throws Exception {
        System.setProperty("spring.config.name", "application,application-kurento");
        SpringApplication.run(ModuleKurentoApplication.class, args);
    }

}

