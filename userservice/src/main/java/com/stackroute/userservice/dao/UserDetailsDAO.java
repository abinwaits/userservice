package com.stackroute.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.userservice.model.UserDetails;

public interface UserDetailsDAO extends JpaRepository<UserDetails,String>{
	
	UserDetails findByEmailId(String emailId);

}
