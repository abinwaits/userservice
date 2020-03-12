/**
 * 
 */
package com.stackroute.userservice.dto;

/**
 * @author ubuntu
 *
 */
public class ApiResponse {
	
	private String message;
	private Integer httpStatus;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
}
