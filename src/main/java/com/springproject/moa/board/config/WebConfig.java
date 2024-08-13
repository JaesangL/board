package com.springproject.moa.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 경로 허용
//					.allowedOrigins("*") // 지정된 출처 허용
					.allowedOriginPatterns("*") // Credentials가 true일 경우에 allowedOrigins대신 사용
					.allowedMethods("*") // 모든 HTTP 메서드 허용
					.allowedHeaders("*") // 모든 헤더 허용
					.allowCredentials(true); // 쿠키 및 인증 정보 허용
	}
	
}
