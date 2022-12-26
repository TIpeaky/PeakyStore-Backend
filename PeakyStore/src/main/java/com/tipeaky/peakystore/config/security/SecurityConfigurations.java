package com.tipeaky.peakystore.config.security;

import com.tipeaky.peakystore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
//@Profile(value = {"prod", "test"})
public class SecurityConfigurations implements WebMvcConfigurer {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, AutenticacaoService autenticacaoDetailService)
			throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(autenticacaoDetailService)
				.passwordEncoder(bCryptPasswordEncoder)
				.and()
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Configuracoes de autorizacao
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/**").permitAll()//.antMatchers(HttpMethod.POST,"/parking-spot").hasRole("user")
				.and().csrf().disable()
//				.anyRequest().authenticated()
//				.and().cors()
//				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}
	
	//Configuracoes de recursos estaticos(js, css, imagens, etc.)
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	
}
