package com.zap.lotes;

import com.zap.lotes.config.properties.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(value = {SecurityProperties.class})
public class ZapMicroservicesLotesApplication {

	public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        SpringApplication.run(ZapMicroservicesLotesApplication.class, args);
	}

}
