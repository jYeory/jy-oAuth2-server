package com.jyeory.sso.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name="oauth_access_token")
public class AccessToken {
	//
	@Id
	@Column(name="token_id")
	private String tokenId;
	
	private String token;
	
	@Column(name="authentication_id")
	private String authenticationId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="client_id")
	private String clientId;
	
	private String authentication;
	
	@Column(name="refresh_token")
	private String refreshToken;
	
}
