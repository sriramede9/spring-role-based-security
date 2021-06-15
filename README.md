# spring-role-based-security

## basic auth

`
http
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
`
## White list some endpoints

`
	http
		.authorizeRequests()
		.antMatchers("/","index.html","/css","/js/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
`

## Add  custom user and roles
`
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails annaSmithUser = User.builder()
		.username("annasmith")
		.password(passwordEncoder.encode("password"))
		.roles("STUDENT")
		.build();
		
		return new InMemoryUserDetailsManager(annaSmithUser);
	}
`
