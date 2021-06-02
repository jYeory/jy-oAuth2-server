package com.jyeory.sso.oauth.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class UserInfoResponse {
	private boolean result = true;
	private String message;
	
	private String username;
	private String userId;
	private String userCd;
	
}
