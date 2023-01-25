package com.ssafy.api.service;

import com.ssafy.api.request.UserModifyPatchReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.entity.User;
import com.ssafy.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

/**
 * 유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRegisterPostReq userRegisterInfo) {
        User user = new User();
        user.setUserEmail(userRegisterInfo.getEmail());
        // 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
        user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));

        user.setName(userRegisterInfo.getName());
        user.setPosition(userRegisterInfo.getPosition());
        user.setDepartment(userRegisterInfo.getDepartment());

        return userRepository.save(user);
    }

    @Override
    public User getUserByUserEmail(String userEmail) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회).
        return userRepository.findByUserEmail(userEmail).orElseThrow(NoSuchElementException::new);
    }


    @Override
    public void updateUser(String userEmail, UserModifyPatchReq req) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(NoSuchElementException::new);

        user.setName(req.getName());
        user.setDepartment(req.getDepartment());
        user.setPosition(req.getPosition());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
    }

    @Override
    public boolean checkExist(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(NoSuchElementException::new);
        if(user != null){
            return true;
        }
        return false;
    }

}
