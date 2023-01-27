package com.idle.web.interceptor;

import com.idle.oauth.api.OauthLoginApi;
import com.idle.oauth.exception.ExpiredAccessTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.*;

@Component
@RequiredArgsConstructor
public class AccessTokenValidationInterceptor implements HandlerInterceptor {

    private final OauthLoginApi oauthLoginApi;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = request.getHeader("Authorization");
        try {
            if (isForbidden(accessToken)) {
                response.setStatus(FORBIDDEN.value());
                System.out.println(accessToken);
                return false;
            }
//            kakaoLoginApi.getMember("Bearer", accessToken);
        } catch (ExpiredAccessTokenException e) {
            response.setStatus(UNAUTHORIZED.value());
            return false;
        }
        return true;
    }

    public boolean isForbidden(String accessToken) {
        return !StringUtils.hasText(accessToken);
    }
}
