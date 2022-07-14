package com.zap.lotes.config.feign;

import com.zap.lotes.config.jwt.InternalTokenService;
import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
@RequiredArgsConstructor
public class ZapClientFeignConfig {

    private final InternalTokenService internalTokenService;

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            try {
                requestTemplate.header("Authorization", internalTokenService.getBearer());
            } catch (Exception e) {
                log.error("Erro a gerar token de uso interno");
            }
        };
    }

}
