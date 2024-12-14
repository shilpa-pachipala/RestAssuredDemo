import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class Payloadinfo {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.Addplace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).header("Server",equalTo("Apache/2.4.52 (Ubuntu)"))
		.extract().response().asString();

		JsonPath js=new JsonPath(response); //for parsing Json
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//Add place done>>update place with new address
		String newAddress="Chandra nivas";
		given().log().all().queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


		//get place
		
	String getplaceresponse=	given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId )
		.when().get("maps/api/place/get/json") 
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=new JsonPath(getplaceresponse);
		String UpdatedAddress=js1.getString("address");
		
		System.out.println(UpdatedAddress);
		Assert.assertEquals(UpdatedAddress,newAddress );
	}

}
