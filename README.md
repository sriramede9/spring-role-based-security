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

## Add roles of type ENUM with Permissions

`
	http
		.authorizeRequests()
		.antMatchers("/","index.html","/css","/js/**").permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
`

## Separate API requests based on Roles [who can post and who can only read]

`
	http
		.authorizeRequests()
		.antMatchers("/","index.html","/css","/js/**").permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.antMatchers(HttpMethod.GET,"/management/**").hasRole(ApplicationUserRole.ADMINTRAINEE.name())
		.antMatchers(HttpMethod.POST,"/management/**").hasRole(ApplicationUserRole.ADMIN.name())
		.antMatchers(HttpMethod.PUT,"/management/**").hasRole(ApplicationUserRole.ADMIN.name())
		.antMatchers(HttpMethod.DELETE,"/management/**").hasRole(ApplicationUserRole.ADMIN.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
`


## based on Authorities

`
	http
		.authorizeRequests()
		.antMatchers("/","index.html","/css","/js/**").permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMINTRAINEE.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
`
