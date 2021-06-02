package com.jyeory.sso.oauth.repository;

import org.springframework.data.repository.CrudRepository;

import com.jyeory.sso.oauth.entity.Client;

public interface ClientRepository extends CrudRepository<Client, String> {
	//
}
