package com.jyeory.sso.web.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//@Entity
//@Setter
//@Getter
//@Table(name="USER_ROLE")
public class UserRole {
	
//	@Id
//	@JoinColumn(name="USER_ID")
	private String userId;
	
//	@Id
//	@OneToMany
//	@JoinColumn(name="ROLE_CODE")
	private List<Role> roles;

}
