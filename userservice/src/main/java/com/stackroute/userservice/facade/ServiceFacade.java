package com.stackroute.userservice.facade;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceFacade {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${favouriteServiceURI}")
	private String favouriteServiceURI;

	public String getJWTToken(String userName, String password) {
		String token = "";
		try {
			StringBuilder finalURI = new StringBuilder(favouriteServiceURI).append("/authenticate/").append(userName)
					.append("/").append(password);
			System.out.println("Final URL >>" + finalURI.toString());
			HttpHeaders headers = getHeaders();
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(finalURI.toString(), HttpMethod.POST, entity,
					String.class);
			token = response.getBody();
		}catch(Exception e) {
			System.out.println("Error in invoking favourite service: "+e.getMessage());
			e.printStackTrace();
		}
		
		return token;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

}
