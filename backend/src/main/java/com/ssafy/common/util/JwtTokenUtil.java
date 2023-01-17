package com.ssafy.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.common.auth.RefreshToken;
import com.ssafy.common.auth.SsafyUserDetailService;
import com.ssafy.common.auth.TokenInfo;
import com.ssafy.db.entity.Role;
import com.ssafy.db.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;

/**
 * jwt 토큰 유틸 정의.
 */
@Component
@NoArgsConstructor
public class JwtTokenUtil {

    @Autowired
    private static UserRepository userRepository;

    private static RedisRepository redisRepository;

    private static String secretKey;

    private static SsafyUserDetailService ssafyUserDetailService = new SsafyUserDetailService(userRepository);
    private static final long ACCESS_TOKEN_EXPIRE_TIME =  30 * 60 * 1000L;  // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;    // 7일

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ISSUER = "ResetContent";

    @Autowired
    public JwtTokenUtil(UserRepository userRepository, RedisRepository redisRepository, @Value("${jwt.secret}") String secretKey) {
        this.userRepository = userRepository;
        this.redisRepository = redisRepository;
        this.secretKey = secretKey;
    }


    public static TokenInfo generateToken(String userId){

        String accessToken = createAccessToken(userId);
        String refreshToken = createRefreshToken(userId);
        redisRepository.save(new RefreshToken(userId,createRefreshToken(userId),REFRESH_TOKEN_EXPIRE_TIME));

        return TokenInfo.builder()
                .userId(userId)
                .grantType(TOKEN_PREFIX)
                .authorization(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
                .build();

    }

    public static String createAccessToken(String userId) {
        Date now = new Date();
        Date expires =  new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(expires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }

    public static String createRefreshToken(String userId) {
        Date now = new Date();
        Date expires =  new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME);
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(expires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }
    // 토큰에서 회원 정보 추출

    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(getSigningKey(secretKey)).parseClaimsJws(token).getBody().getSubject();
    }

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public Claims extractAllClaims(String jwtToken) { // 2
        return Jwts.parserBuilder().setSigningKey(getSigningKey(secretKey))
                .build().parseClaimsJws(jwtToken)
                .getBody();
    }


    public boolean validateToken(String jwtToken) {
            Date expiration = extractAllClaims(jwtToken).getExpiration();
            return expiration.before(new Date());
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = ssafyUserDetailService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    //재발급
    public String reissueAccessToken(String userId) throws JsonProcessingException {
        RefreshToken refreshToken = redisRepository.findById(userId).get();
        if (Objects.isNull(refreshToken)) {

        }
        String accessToken = createAccessToken(userId);
        return accessToken;
    }


    public String resolveAccessToken(HttpServletRequest request) {
        if(request.getHeader("authorization") != null )
            return request.getHeader("authorization");
        return null;
    }
    public String resolveRefreshToken(HttpServletRequest request) {
        if(request.getHeader("refreshToken") != null )
            return request.getHeader("refreshToken");
        return null;
    }

    // UserId로 권한 정보 가져오기
    public Role getRoles(String userId) {
        return userRepository.findByUserId(userId).get().getRole();
    }
}
