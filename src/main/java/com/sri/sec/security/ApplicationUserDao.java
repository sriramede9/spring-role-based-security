package com.sri.sec.security;

import java.util.Optional;

public interface ApplicationUserDao {

	public Optional<ApplicationUser> selectApplicationUserByUserName(String username);
}
