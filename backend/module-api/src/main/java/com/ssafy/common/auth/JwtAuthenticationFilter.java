package com.ssafy.common.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssafy.common.util.JwtTokenUtil;

/**
 * 요청 헤더에 jwt 토큰이 있는 경우, 토큰 검증 및 인증 처리 로직 정의.
 */

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Read the Authorization header, where the JWT Token should be
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtTokenUtil.resolveAccessToken(request);

        // 유효한 토큰인지 확인합니다.
        if (accessToken != null) {
            // 어세스 토큰이 유효한 상황
            if (jwtTokenUtil.validateToken(accessToken)) {
                this.setAuthentication(accessToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    // SecurityContext 에 Authentication 객체를 저장합니다.
    public void setAuthentication(String token) {
        // 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = jwtTokenUtil.getAuthentication(token);
        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
