package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPITest {

	Map<Object, Object> tokenMap;
	String accessToken;
	String accountUserName;

	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUserName = tokenMap.get("account_username").toString();
	}

	@Test
	public void getAccountBlockStatus() {
		Map<String,String> authTokenMap=Token.getAuthToken();
		
		Response response = RestClient.doGet("JSON", "https://api.imgur.com",
				"/account/v1/" + accountUserName + "/block", authTokenMap, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());

	}

	@Test
	public void getAccountImagesTest() {
		
		Map<String,String> authTokenMap=Token.getAuthToken();
		
		Response response = RestClient.doGet(null, "https://api.imgur.com", "/3/account/me/images", authTokenMap, null,
				true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	@Test
	public void uploadImagePostAPITest() {
		
		Map<String,String> clientIdMap=Token.getClientId();
		
		Map<String,String> formMap=new HashMap<>();
		formMap.put("title", "test title api");
		formMap.put("description", "Test description API");
		
		Response response=RestClient.doPost("multipart", "https://api.imgur.com", "/3/upload", clientIdMap, null, true, formMap);
	
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
}
