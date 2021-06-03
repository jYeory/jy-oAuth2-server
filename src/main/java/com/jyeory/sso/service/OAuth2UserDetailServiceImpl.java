package com.jyeory.sso.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jyeory.sso.web.entity.UserInfo;
import com.jyeory.sso.web.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userDetailsService")
public class OAuth2UserDetailServiceImpl implements OAuth2UserDetailService {

	@Autowired
	private UserInfoRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("\t\t loadUserByUsername username : " + username);
		
		Optional<UserInfo> user = userRepo.findByUserId(username);
		
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("Invalid resource owner, please check resource owner info !");
		}

		return user.get();
	}
	
}
