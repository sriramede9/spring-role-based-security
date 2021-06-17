package com.sri.sec.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class FakeAppliDao implements ApplicationUserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
		// TODO Auto-generated method stub
		return getApplicationUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {


		ApplicationUser annaSmithApplicationUser = new ApplicationUser(
				ApplicationUserRole.STUDENT.getGrantedAuthorities(), passwordEncoder.encode("pwd"), "anna", true, true,
				true, true);
		ApplicationUser lindaApplicationUser = new ApplicationUser(ApplicationUserRole.ADMIN.getGrantedAuthorities(),
				passwordEncoder.encode("pwd123"), "linda", true, true, true, true);
		ApplicationUser tomApplicationUser = new ApplicationUser(
				ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities(), passwordEncoder.encode("pwd123"), "tom", true,
				true, true, true);

		return Arrays.asList(annaSmithApplicationUser, lindaApplicationUser, tomApplicationUser);
	}

}
