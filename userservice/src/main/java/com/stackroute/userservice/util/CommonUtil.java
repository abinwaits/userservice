package com.stackroute.userservice.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 */

/**
 * @author ubuntu
 *
 */
public class CommonUtil {
	private CommonUtil() {
		
	}
	
	public static java.sql.Date getSqlData(String stringDate) throws ParseException{
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = sdf1.parse(stringDate);
		return new java.sql.Date(date.getTime());
	}
	
	public static String[] filterCredentials(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		return new String(Base64.getDecoder().decode(authToken)).split(":");
	}
}
