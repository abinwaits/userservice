/**
 * 
 */
package com.stackroute.userservice.service;

import javax.servlet.http.HttpServletRequest;

import com.stackroute.userservice.dto.ApiResponse;
import com.stackroute.userservice.dto.UserData;
import com.stackroute.userservice.dto.UserResponse;

/**
 * @author ubuntu
 *
 */
public interface IUserService {
	
	ApiResponse saveUser(UserData userData);
	
	UserResponse authenticateUser(HttpServletRequest request);

}
