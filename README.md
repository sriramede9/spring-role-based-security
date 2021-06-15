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
