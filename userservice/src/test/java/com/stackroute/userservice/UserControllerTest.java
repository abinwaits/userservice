package com.stackroute.userservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.stackroute.userservice.dto.ApiResponse;
import com.stackroute.userservice.dto.UserData;
import com.stackroute.userservice.dto.UserResponse;

public class UserControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void saveUser() throws Exception {
		String uri = "/user";
		UserData userData = new UserData();
		userData.setEmailId("sachin50@gmail.com");
		userData.setFirstName("Sachin");
		userData.setLastName("Tendulkar");
		userData.setDob("25-12-1990");
		userData.setPassword("Password123");
		String inputJson = super.mapToJson(userData);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ApiResponse apiResponse = super.mapFromJson(content, ApiResponse.class);
		assertEquals(apiResponse.getMessage(), "User saved successfully");

	}

	@Test
	public void login() throws Exception {
		String uri = "/login";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).headers(getHeaders()))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		UserResponse userResponse = super.mapFromJson(content, UserResponse.class);
		assertEquals(userResponse.isUserAuthentic(), true);
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = createHeaders("sachin50@gmail.com", "Password123");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	@Test
	public void loginNegative() throws Exception {
		String uri = "/login";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.headers(getHeadersNegative())).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		UserResponse userResponse = super.mapFromJson(content, UserResponse.class);
		assertEquals(userResponse.isUserAuthentic(), false);
	}

	private HttpHeaders getHeadersNegative() {
		HttpHeaders headers = createHeaders("sachin50@gmail.com", "Password12345");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

}
