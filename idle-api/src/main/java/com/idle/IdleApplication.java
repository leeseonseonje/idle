package com.idle;

import com.idle.oauth.api.dto.KakaoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KakaoProperties.class)
public class IdleApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleApplication.class, args);
    }
}
