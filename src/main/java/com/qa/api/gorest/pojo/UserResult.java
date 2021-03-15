package com.qa.api.gorest.pojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserResult {

	@Test
	public void createUserWithFullJson() {
		String token = "5c0be83a212c355886eda936d41fe0e1108cd47940062c895b93515377040420";
		
		Self sf = new Self("http://www.sf.com");
		Edit ed = new Edit("http://www.ed.com");
		Avatar av = new Avatar("http://www.av.com");
	
		Links ln = new Links(sf, ed, av);
		
		UserInfo uf = new UserInfo("Tom", "Peter", "Male", "09-09-1998", "tom972@gmail.com", "89898899",
				"http://www.tom.com", "test address", "Active", ln);
		
		String userJsonPayload=TestUtil.getSerializedJSON(uf);
		System.out.println(userJsonPayload);
		
//		Map<String, String> authTokenMap = new HashMap<String, String>();
//		authTokenMap.put("Authorization", "Bearer " + token);
//		Response response = RestClient.doPost("JSON", "https://gorest.co.in", "/public-api/users", authTokenMap, null, true, userJsonPayload);
//		System.out.println(response.getStatusCode());
//		System.out.println(response.prettyPrint());
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 5c0be83a212c355886eda936d41fe0e1108cd47940062c895b93515377040420")
		.body(userJsonPayload)
			.post("/public-api/users")
		.then()
			.assertThat()
				.contentType(ContentType.JSON)
				.statusCode(200);
			
	}
}
