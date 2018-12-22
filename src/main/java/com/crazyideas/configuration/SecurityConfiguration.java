package com.lambdasoft.risquesmanagement.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	private final String USERS_QUERY = "select code, password, active from utilisateurs where code=upper(?)";
	private final String ROLES_QUERY = "select u.code, r.role from utilisateurs u inner join utilisateur_role ur on (u.code = ur.utilisateur_code) inner join roles r on (ur.role_id=r.role_id) where u.code=upper(?)";

	private final String USER = "USER";
	private final String ADMIN = "ADMIN";
	
	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.usersByUsernameQuery(USERS_QUERY)
			.authoritiesByUsernameQuery(ROLES_QUERY)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
        	.antMatchers("/css/**", "/fonts/**", "/js/**", "/images/**", "/webjars/**").permitAll()
			.antMatchers("/", "/login").permitAll()
			.antMatchers("/register", "/categories/**","/modeles/**","/processus/**","/criteres/**").hasAuthority(ADMIN)
			.antMatchers("/dashboard/**","/risques/**").hasAnyAuthority(ADMIN,USER)
			.anyRequest().authenticated()
			.and().csrf().disable()
			.formLogin().loginPage("/login").failureUrl("/login?error=true")
			.defaultSuccessUrl("/dashboard")
			.usernameParameter("code")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
			.and().rememberMe()
			.and().exceptionHandling().accessDeniedPage("/access_denied");
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
	
		return db;
	}
}
