package com.sri.sec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.sri.sec.jwt.JwtUsernameAndPasswordAuthFilter;

import static com.sri.sec.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FakeAppliDao fakeAppliDao;
	
	@Autowired
	private ApplicationService applicationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf()
		.disable();
		
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager()))
		.authorizeRequests()
		.antMatchers("/","index.html","/login","/css","/js/**").permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.anyRequest()
		.authenticated();
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.defaultSuccessUrl("/courses")
//		.and()
//		.rememberMe() //default two weeks
//		
//		.and()
//		.logout()
//		.logoutUrl("/logout")
//		.clearAuthentication(true)
//		.invalidateHttpSession(true)
//		.deleteCookies("JSESSIONID","remember-me")
//		.logoutSuccessUrl("/login");
	}

	
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(daoAuthenticationProvider());
	}





	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(applicationService);
		return daoAuthenticationProvider;
	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//
////		UserDetails annaSmithUser = User.builder()
////		.username("annasmith")
////		.password(passwordEncoder.encode("password"))
//////		.roles(STUDENT.name())
////		.authorities(STUDENT.getGrantedAuthorities())
////		.build();
////		
////		UserDetails lindaUser = User.builder()
////				.username("linda")
////				.password(passwordEncoder.encode("password123"))
//////				.roles(ADMIN.name())
////				.authorities(ADMIN.getGrantedAuthorities())
////				.build();
////		
////		UserDetails tomUser = User.builder()
////				.username("tom")
////				.password(passwordEncoder.encode("password123"))
//////				.roles(ADMINTRAINEE.name())
////				.authorities(ADMINTRAINEE.getGrantedAuthorities())
////				.build();
////		
////		return new InMemoryUserDetailsManager(annaSmithUser,lindaUser,tomUser);
//		
//		
//	}
	
	

	
}
