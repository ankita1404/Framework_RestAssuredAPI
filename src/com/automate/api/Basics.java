package com.automate.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import Files.payload;
import Files.resuableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

//****Eclipse will not give auto suggestion for static Package

public class Basics {

	public static void main(String[] args) {
		
		//given - all input details
		//when - Submit the API - http method
		//Then - validate the response		
		
		RestAssured.baseURI ="https://unipupil.com";
		String Response =given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(Response);
		JsonPath js = new JsonPath(Response); //for parsing json
		String placeId =js.getString("place_id");
		
		System.out.println("placeID" +"    " + placeId);
		
		String newAddress ="Summer Walk , Africa";
		//update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"").
		when().put("maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 =resuableMethod.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		
	}
}
