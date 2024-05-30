package com.green.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정하는 class예요! 라는 의미로 이 어노테이션 붙여준다.
@EnableWebSecurity // spring security 처리용 어노테이션
public class SecurityConfig {
	// 반환타입을 BCryptPasswordEncoder 로 설정 (패스워드 암호화하고 반환하는거 받는 타입)

	@Bean // 매서드에서 반환하는 것을 빈으로 등록할때 쓰는 어노테이션 @Bean --> 필요할때 autowired 해서 꺼내쓰면됨
	public BCryptPasswordEncoder bcCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Bean // 인가를 위한 객체
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth			// 화살표 함수: 람다 표현식(스트링부트 최신 버전에서는 람다식을 써야함. 안쓰면 오류남.)
		.requestMatchers("/", "/registForm", "/registProc").permitAll()			// 요청을 통해 오는 애들은 전부 허용하겠다.
		.requestMatchers("/members/**").hasAnyRole("ADMIN", "MEMBER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()			//anyRequest(): "지정한 친구들 말고 나머지는 다 인증을 거쳐야 함."을 나타냄
		);
		
		
		http.formLogin((auth) -> auth
				.loginPage("/login")						// 로그인 폼 지정 - 쓰지 않으면 Spring Security가 제공하는 로그인 폼 사용
				.loginProcessingUrl("/loginProc")			// 실제 로그인을 처리하는 애 (로그인 폼 지정 후 폼 파라미터를 보내는 경로지정), but controller에 따로 만들지 않음. secutiry에서 알아서 처리해줌
				.defaultSuccessUrl("/")
				.permitAll()								
				);
		
		http.csrf(AbstractHttpConfigurer::disable);			// csrf 기능 끄기 (in regiForm)
		
		return http.build();
	}
	
	
}
