package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "5c0be83a212c355886eda936d41fe0e1108cd47940062c895b93515377040420";

	@Test
	public void getAllUsersListAPITest() {
		Map<String,String> authTokenMap=new HashMap<>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		Response response=RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		int statusCode=response.getStatusCode();
		
		AssertJUnit.assertEquals(statusCode, 200	);
	}
	
	@Test
	public void getUsersListWithQueryParamsAPITest() {
		Map<String,String> authTokenMap=new HashMap<>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		Map<String,String> params=new HashMap<>();
		params.put("name", "Charak");
		params.put("gender","Female");
		
		Response response=RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		int statusCode=response.getStatusCode();
		
		AssertJUnit.assertEquals(statusCode, 200);
	}
}
