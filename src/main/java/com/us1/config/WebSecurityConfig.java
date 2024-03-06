package com.us1.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.us1.filter.JwtFilter;
import com.us1.handler.HttpAuthenticationEntryPoint;
import com.us1.service.CustomUserServiceImpl;
import com.us1.utils.ApplicationUriConstant;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // for pre-post global security
public class WebSecurityConfig {
	
	@Autowired
	private CustomUserServiceImpl customUserServiceImpl;
	
	@Autowired
	private HttpAuthenticationEntryPoint httpAuthenticationEntryPoint;

    @Bean
    public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	  http
    	  		.csrf(csrf -> csrf.disable())
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                     auth.requestMatchers(ApplicationUriConstant.API + ApplicationUriConstant.V1 +ApplicationUriConstant.LOGIN).permitAll();
                     auth.requestMatchers(ApplicationUriConstant.API + ApplicationUriConstant.V1 +ApplicationUriConstant.DOUBLE_HASHTAG).authenticated();
                
                 }); 
    	  http
    	  	   .addFilterBefore(new JwtFilter(customUserServiceImpl), UsernamePasswordAuthenticationFilter.class);
    	  http
    	  		.exceptionHandling(exception -> exception.authenticationEntryPoint(httpAuthenticationEntryPoint));
    	  
    	  return http.build();
    	  
//       return http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .requestMatchers(ApplicationUriConstant.LOGIN)
//                .permitAll()
////                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html",
////                		"/swagger-resources/**", "/webjars/**","/login").permitAll()
//                .requestMatchers("/users").authenticated()
//                .and()
//                .csrf().disable()
//                .cors().and()
//                .addFilterBefore(new JwtFilter(customUserServiceImpl), UsernamePasswordAuthenticationFilter.class)
//                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}