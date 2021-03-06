/**
 * 
 */
package com.stackroute.userservice.service.impl;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.stackroute.userservice.dao.UserDetailsDAO;
import com.stackroute.userservice.dto.ApiResponse;
import com.stackroute.userservice.dto.UserData;
import com.stackroute.userservice.dto.UserResponse;
import com.stackroute.userservice.facade.ServiceFacade;
import com.stackroute.userservice.model.UserDetails;
import com.stackroute.userservice.service.IUserService;
import com.stackroute.userservice.util.CommonUtil;

/**
 * @author ubuntu
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserDetailsDAO userDetailsDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	ServiceFacade serviceFacade;

	@Override
	public ApiResponse saveUser(UserData userData) {
		// TODO Auto-generated method stub
		UserDetails userDetails = null;
		ApiResponse apiResponse = new ApiResponse();
		if (userData != null) {
			if (!StringUtils.isEmpty(userData.getEmailId())) {
				UserDetails userDetailsDB = userDetailsDAO.findByEmailId(userData.getEmailId());
				if (userDetailsDB != null && !StringUtils.isEmpty(userDetailsDB.getEmailId())) {
					apiResponse.setMessage("Email_id already exists");
					apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
				} else {
					userDetails = new UserDetails();
					userDetails.setEmailId(userData.getEmailId());
					userDetails.setFirstName(userData.getFirstName());
					userDetails.setLastName(userData.getLastName());
					if (userData.getDob() != null) {
						try {
							userDetails.setDob(CommonUtil.getSqlData(userData.getDob()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					userDetails.setPassword(passwordEncoder.encode(userData.getPassword()));
					userDetailsDAO.save(userDetails);
					apiResponse.setMessage("User saved successfully");
					apiResponse.setHttpStatus(HttpStatus.OK.value());
				}

			} else {
				apiResponse.setMessage("Bad request");
				apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
			}

		}
		return apiResponse;
	}

	@Override
	public UserResponse authenticateUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String[] credentials = CommonUtil.filterCredentials(request);
		UserResponse userResponse = new UserResponse();
		userResponse.setEmailId(credentials[0]);
		userResponse.setUserAuthentic(false);
		userResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		UserDetails userDetails = userDetailsDAO.findByEmailId(credentials[0]);
		if (userDetails != null && !StringUtils.isEmpty(userDetails.getEmailId())
				&& passwordEncoder.matches(credentials[1], userDetails.getPassword())) {
			userResponse.setUserAuthentic(true);
			userResponse.setHttpStatus(HttpStatus.OK.value());
			String fullName = userDetails.getFirstName();
			if (!StringUtils.isEmpty(userDetails.getLastName())) {
				fullName = fullName + " " + userDetails.getLastName();
			}
			userResponse.setFullName(fullName);
			userResponse.setToken(serviceFacade.getJWTToken(credentials[0], credentials[1]));
		}
		return userResponse;
	}

}
