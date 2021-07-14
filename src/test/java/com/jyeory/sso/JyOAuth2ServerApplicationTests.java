package com.jyeory.sso;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jyeory.sso.web.entity.Role;
import com.jyeory.sso.web.entity.UserInfo;
import com.jyeory.sso.web.repository.RoleRepository;
import com.jyeory.sso.web.repository.UserInfoRepository;

@SpringBootTest
class JyOAuth2ServerApplicationTests {

	@Autowired
	private UserInfoRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
	
	@Test
	void addUser() {
		UserInfo user = new UserInfo();
		user.setUserCd("U21060300000");
		user.setUserId("test01");
		user.setPassword(passwordEncoder().encode("test01"));
		user.setName("test01");
		user.setEmail("test01@jyeory.com");
		
		Role role = new Role();
		role.setRoleCd("ROLE999999");
		role.setRoleName("Tester");
		
		List<Role> userRole = new ArrayList<>();
		userRole.add(role);
		user.setUserRoles(userRole);
		
		user = userRepo.save(user);
		
		
		System.out.println(user.getRegDtm());
		
	}

}
