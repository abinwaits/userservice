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
