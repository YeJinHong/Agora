package com.ssafy.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.ssafy.common.auth.CustomUserDetailService;
import com.ssafy.common.auth.RefreshToken;
import com.ssafy.common.auth.TokenInfo;
import com.ssafy.db.entity.Role;
import com.ssafy.db.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * jwt 토큰 유틸 정의.
 */
@Component
public class JwtTokenUtil {


    private UserRepository userRepository;

    private RedisRepository redisRepository;

    private CustomUserDetailService customUserDetailService;
    private static String secretKey;

    private static final long ACCESS_TOKEN_EXPIRE_TIME =  30 * 60 * 1000L;  // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;    // 7일


    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ISSUER = "ResetContent";

    @Autowired
    public JwtTokenUtil(UserRepository userRepository, RedisRepository redisRepository, CustomUserDetailService customUserDetailService,
                        @Value("${jwt.secret}") String secretKey) {
        this.userRepository = userRepository;
        this.redisRepository = redisRepository;
        this.customUserDetailService = customUserDetailService;
        this.secretKey = secretKey;
    }

    //토큰 생성
    public TokenInfo generateToken(String userId){

        String accessToken = createToken(userId,ACCESS_TOKEN_EXPIRE_TIME);
        String refreshToken = createToken(userId,REFRESH_TOKEN_EXPIRE_TIME);
        redisRepository.save(new RefreshToken(userId,refreshToken,REFRESH_TOKEN_EXPIRE_TIME));

        return TokenInfo.builder()
                .userId(userId)
                .grantType(TOKEN_PREFIX)
                .authorization(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpirationTime(ACCESS_TOKEN_EXPIRE_TIME)
                .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
                .build();

    }


    public static String createToken(String userId, Long expireTime) {
        Date now = new Date();
        Date expires =  new Date(now.getTime() + expireTime);
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(expires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }

    // 토큰에서 회원 정보(아이디) 추출
    public String getUserId(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build().parseClaimsJws(jwtToken).getBody().getSubject();
    }

    //토큰 유효시간 체크
    public boolean validateToken(String jwtToken) {
        Date expiration = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build().parseClaimsJws(jwtToken)
                .getBody().getExpiration();
        return expiration.before(new Date());
    }


    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserId(token.substring(7)));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
    // accessToken HEADER 체크
    public String resolveAccessToken(HttpServletRequest request) {
        if(request.getHeader("authorization") != null )
            return request.getHeader("authorization");
        return null;
    }

    public Role getRoles(String userId) {
        return userRepository.findByUserId(userId).get().getRole();
    }
    
    //    public static void handleError(String token) {
//        JWTVerifier verifier = JWT
//                .require(Algorithm.HMAC512(secretKey.getBytes()))
//                .withIssuer(ISSUER)
//                .build();
//
//        try {
//            verifier.verify(token.replace(TOKEN_PREFIX, ""));
//        } catch (AlgorithmMismatchException ex) {
//            throw ex;
//        } catch (InvalidClaimException ex) {
//            throw ex;
//        } catch (SignatureGenerationException ex) {
//            throw ex;
//        } catch (SignatureVerificationException ex) {
//            throw ex;
//        } catch (TokenExpiredException ex) {
//            throw ex;
//        } catch (JWTCreationException ex) {
//            throw ex;
//        } catch (JWTDecodeException ex) {
//            throw ex;
//        } catch (JWTVerificationException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw ex;
//
//        }
//    }
}
