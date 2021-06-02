package com.jyeory.sso.oauth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jyeory.sso.oauth.entity.AccessToken;

public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {
	//
	AccessToken findByTokenIdAndClientId(String tokenId, String clientId);
	
	int deleteByUserName(String userName);
	
	List<AccessToken> findByUserName(String userName);
}
