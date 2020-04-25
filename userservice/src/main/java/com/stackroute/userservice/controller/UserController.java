/**
 * 
 */
package com.stackroute.userservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ApiResponse> saveUser(@RequestBody UserData userData) {
		ResponseEntity<ApiResponse> responseEntity = null;
		ApiResponse apiResponse = userService.saveUser(userData);
		if (apiResponse != null) {
			if (apiResponse.getHttpStatus() == 200) {
				responseEntity = new ResponseEntity(apiResponse, HttpStatus.OK);
			} else if (apiResponse.getHttpStatus() == 400) {
				responseEntity = new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
			}
		}
		return responseEntity;
	}

	@RequestMapping("/login")
	public ResponseEntity<UserResponse> authenticateUser(HttpServletRequest request) {
		ResponseEntity<UserResponse> responseEntity = null;
		UserResponse userResponse=userService.authenticateUser(request);
		if (userResponse != null) {
			if (userResponse.getHttpStatus() == 200) {
				responseEntity = new ResponseEntity(userResponse, HttpStatus.OK);
			} else if (userResponse.getHttpStatus() == 400) {
				responseEntity = new ResponseEntity(userResponse, HttpStatus.BAD_REQUEST);
			}
		}
		return responseEntity;
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request) {
		request.getSession().invalidate();
	}

}
