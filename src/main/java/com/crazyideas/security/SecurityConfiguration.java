package com.crazyideas.security;

import com.crazyideas.configuration.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.crazyideas.security.SecurityConstants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final CustomUserDetailsService customUserDetailsService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}


	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
            .userDetailsService(this.customUserDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
            .authorizeRequests()
			.antMatchers("/", "/register", "/login").permitAll()
			.antMatchers("/ideas/**").hasAnyAuthority(USER,ADMIN)
			.anyRequest().authenticated()
			.and().csrf().disable()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailsService))
			.rememberMe();
	}
}