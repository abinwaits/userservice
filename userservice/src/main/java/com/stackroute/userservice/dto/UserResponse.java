/**
 * 
 */
package com.stackroute.userservice.dto;

/**
 * @author ubuntu
 *
 */
public class UserResponse {
	private String emailId;
	private boolean isUserAuthentic;
	private String token;
	private Integer httpStatus;
	private String fullName;
	
		
	public Integer getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public boolean isUserAuthentic() {
		return isUserAuthentic;
	}
	public void setUserAuthentic(boolean isUserAuthentic) {
		this.isUserAuthentic = isUserAuthentic;
	}
	
	
}
