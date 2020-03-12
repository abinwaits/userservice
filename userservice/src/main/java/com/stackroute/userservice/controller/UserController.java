/**
 * 
 */
package com.stackroute.userservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.dto.ApiResponse;
import com.stackroute.userservice.dto.UserData;
import com.stackroute.userservice.dto.UserResponse;
import com.stackroute.userservice.service.IUserService;

/**
 * @author ubuntu
 *
 */

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	IUserService userService;

	@PostMapping("/user")
	public ApiResponse saveUser(@RequestBody UserData userData) {
		return userService.saveUser(userData);
	}

	@RequestMapping("/login")
	public UserResponse authenticateUser(HttpServletRequest request) {
		return userService.authenticateUser(request);
	}

}
