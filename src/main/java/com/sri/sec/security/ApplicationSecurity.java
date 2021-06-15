package com.sri.sec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.sri.sec.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf()
		.disable();
		
		http
		.authorizeRequests()
		.antMatchers("/","index.html","/css","/js/**").permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails annaSmithUser = User.builder()
		.username("annasmith")
		.password(passwordEncoder.encode("password"))
		.roles(STUDENT.name())
		.build();
		
		UserDetails lindaUser = User.builder()
				.username("linda")
				.password(passwordEncoder.encode("password123"))
				.roles(ADMIN.name())
				.build();
		
		return new InMemoryUserDetailsManager(annaSmithUser,lindaUser);
	}
	
	

	
}
