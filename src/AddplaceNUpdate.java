import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddplaceNUpdate {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house123\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"83PS,Sainivas, HYderabad\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "").when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString()
;		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		System.out.println(js.getString("status").toString());
		
		
		//update place
		String updatedAddress="LakshanaNivas";
		
		
		 given().log().all().queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+updatedAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "") .when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		 //Get address and compare
		 String newAddress=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId )
			.when().get("maps/api/place/get/json") 
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
		 
		 JsonPath js1=new JsonPath(newAddress);
		String newadd= js1.get("address");
		Assert.assertEquals(newadd, updatedAddress);
		
		 
		
	} 
	
	
	

}
