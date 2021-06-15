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
