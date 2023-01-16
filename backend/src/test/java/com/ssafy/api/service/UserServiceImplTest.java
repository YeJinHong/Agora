package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
@WebAppConfiguration
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Test
//    @DisplayName("1. 회원가입")
//    public void register(){
//        // given
//        UserRegisterPostReq registerPostReq = new UserRegisterPostReq();
//        registerPostReq.setId("hrlim");
//        registerPostReq.setPassword("qwer1234!!");
//        registerPostReq.setDepartment("삼성소프트웨어아카데이 기술개발팀");
//        registerPostReq.setPosition("사원");
//        registerPostReq.setName("임하림");
//
//        // when
//        userService.createUser(registerPostReq);
//
//        // then
//        User user = userRepository.findByUserId("hrlim").orElseThrow(RuntimeException::new);
//        assertEquals(user.getName(), "임하림");
//    }
}