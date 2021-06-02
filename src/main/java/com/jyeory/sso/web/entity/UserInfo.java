package com.jyeory.sso.web.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Data
@Table(name="USER_INFO")
public class UserInfo implements UserDetails, Serializable, OAuth2User {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String userCd;
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String companyName;
	private Date verifyDate;
	
	@CreationTimestamp
	private Date regDtm;
	
	private Date updateDtm;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			  name = "USER_ROLE", 
			  joinColumns = @JoinColumn(name = "USER_CD"), 
			  inverseJoinColumns = @JoinColumn(name = "ROLE_CD"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> userRoles;

	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		for(Role role : userRoles){
			list.add(new SimpleGrantedAuthority(role.getRoleCd()));
		}
        return list;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
