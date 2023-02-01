package com.ssafy.api.service;

import com.ssafy.api.request.UserModifyPatchReq;
import com.ssafy.api.request.UserReissuePostReq;
import com.ssafy.api.response.UserAuthPostRes;
import com.ssafy.common.auth.TokenInfo;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.entity.rdbms.Role;
import com.ssafy.entity.rdbms.User;
import com.ssafy.entity.redis.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.repository.RedisRepository;
import com.ssafy.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

/**
 * 유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private final RedisRepository redisRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRegisterPostReq userRegisterInfo) {
        User user = User.builder()
                .userEmail(userRegisterInfo.getEmail())
                .password(passwordEncoder.encode(userRegisterInfo.getPassword()))
                .department(userRegisterInfo.getDepartment())
                .grade(userRegisterInfo.getGrade())
                .classNum(userRegisterInfo.getClassNum())
                .name(userRegisterInfo.getName())
                .role(Role.ROLE_USER)
                .position(userRegisterInfo.getPosition())
                .build();
        return userRepository.save(user);
    }

    @Override
    public TokenInfo login(User user) {
        String accessToken = jwtTokenUtil.createAccessToken(user.getUserEmail());
        String refreshToken = jwtTokenUtil.createRefreshToken(user.getUserEmail());

        TokenInfo tokenInfo = jwtTokenUtil.generateToken(user.getUserEmail(), accessToken, refreshToken);

        //redis에 저장
        redisRepository.save(new RefreshToken(user.getUserEmail(), refreshToken, tokenInfo.getRefreshTokenExpirationTime()));

        return tokenInfo;
    }

    @Override
    public ResponseEntity<?> reissue(UserReissuePostReq userReissuePostReq) {
        if(!jwtTokenUtil.validateToken(userReissuePostReq.getRefreshToken())){
            return ResponseEntity.status(401).body(UserAuthPostRes.of(401, "Refresh Token 정보가 유효하지 않습니다.",null));
        }
        Authentication authentication = jwtTokenUtil.getAuthentication(userReissuePostReq.getAccessToken().substring(7));
        if(!redisRepository.findById(authentication.getName()).get().getRefreshToken().equals(userReissuePostReq.getRefreshToken())){
            return ResponseEntity.status(404).body(UserAuthPostRes.of(404, "RefreshToken 정보가 잘못되었습니다..",null));
        }
        String accessToken = jwtTokenUtil.createAccessToken(authentication.getName());
        String refreshToken = jwtTokenUtil.createRefreshToken(authentication.getName());
        TokenInfo tokenInfo = jwtTokenUtil.generateToken(authentication.getName(), accessToken, refreshToken);
        redisRepository.save(new RefreshToken(authentication.getName(), refreshToken ,tokenInfo.getRefreshTokenExpirationTime()));

        return ResponseEntity.ok(UserAuthPostRes.of(200, "Token 정보가 갱신되었습니다.", tokenInfo));
    }

    @Override
    public User getUserByUserEmail(String userEmail) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회)
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
