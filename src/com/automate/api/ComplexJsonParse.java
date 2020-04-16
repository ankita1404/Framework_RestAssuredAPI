package com.automate.api;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		
	
	//Mock API - take some dummy response and continue your automation test
	
	JsonPath js= new JsonPath(payload.CoursePrice());
	int count = js.getInt("courses.size()");
	System.out.println(count);
	
	//print purchase Amount
	int total = js.getInt("dashboard.purchaseAmount");
	System.out.println(total);
	
	//print title of first course
	
	String titleFirstCourse=js.get("courses[0].title");
	System.out.println(titleFirstCourse);
	String titleSecCourse = js.get("courses[1].title");
	System.out.println(titleSecCourse);
	
	System.out.println("--------------------------------------------------------");
	
	for (int i = 0; i < 3; i++) {
		
		String coursetitles =js.get("courses["+i+"].title");
		int prices = js.getInt("courses["+i+"].price");
		System.out.println(coursetitles);
		System.out.println(prices);
		
		}
	
	//condition
	for (int i = 0; i < 3; i++) {
		
		String coursesTitles = js.get("courses["+i+"].title");
		if(coursesTitles.equalsIgnoreCase("RPA")) {
			
		int copies  =js.get("courses["+i+"].copies");
		System.out.println("RPA copies " + " " +copies);
		}
		
	}
	
	}	
} 
