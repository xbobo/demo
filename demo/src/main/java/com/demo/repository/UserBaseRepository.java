package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.UserBase;

public interface UserBaseRepository extends CrudRepository<UserBase, Long> {
	
	UserBase findByUserAccount(String userAccount);

}
