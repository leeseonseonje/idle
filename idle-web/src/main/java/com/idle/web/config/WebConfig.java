package com.idle.web.config;

import com.idle.web.interceptor.AccessTokenValidationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AccessTokenValidationInterceptor accessTokenValidationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenValidationInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/oauth/**", "/logout", "/css/**", "/*.ico", "/error");
    }
}
