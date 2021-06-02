package com.jyeory.sso.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyeory.sso.web.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
