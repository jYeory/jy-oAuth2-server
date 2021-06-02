package com.jyeory.sso.web.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="ROLE")
public class Role {
	@Id
	private String roleCd;
	private String roleName;
	private String description;
	private Date regDtm;
	private Date updateDtm;
	private char useYn;
	
	@ManyToMany(mappedBy="userRoles")
	private List<UserInfo> users;
}
