package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "5c0be83a212c355886eda936d41fe0e1108cd47940062c895b93515377040420";

	@DataProvider(name = "userdata")
	public Object[][] getUserData() {
		Object userdata[][] = ExcelUtil.getData("userdata");
		return userdata;
	}

	@Test(dataProvider = "userdata")
	public void createUserAPIPOSTTest(String name, String email, String gender, String status) {
		Map<String,String> authTokenMap=new HashMap<>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		// User user=new User("Deeps", "deepsi123@gmail.com", "Female", "Active");

		User user = new User(name, email, gender, status);

		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println("=====================================");

	}

	@Test
	public void random() {
		Map<String,String> authTokenMap=new HashMap<>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		int d = (int)(Math.random() * 1000);
		System.out.println(d);
		
		User user=new User("Deeps", "deepsi"+d+"@gmail.com", "Female", "Active");

		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println("=====================================");
	}

}
