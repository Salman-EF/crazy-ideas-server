package com.crazyideas.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

	@Bean
    UserDetailsService thinkerDetailsService(){
	    return new CustomUserDetailsService();
    }
	
	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
            .userDetailsService(thinkerDetailsService())
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
			.formLogin()/*.loginPage("/login")*/.failureUrl("/login?error=true")
			/*.defaultSuccessUrl("/dashboard")*/
			.usernameParameter("email")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
			.and().rememberMe()
			.and().exceptionHandling().accessDeniedPage("/access_denied");
	}
}