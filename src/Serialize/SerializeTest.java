package Serialize;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;


public class SerializeTest {

	public static void main(String[] args) {
		
		Addplace a=new Addplace();
		a.setAccuracy(50);
		a.setName("Frontline house123");
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress("83PS,Sainivas, HYderabad");
		a.setLanguage("French-IN");
		a.setWebsite("http://google.com");
		List<String>mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		a.setTypes(mylist);
		
		Location l=new Location();
		l.setLag(-38.383494);
		l.setLat(33.427362);
		a.setLocation(l);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		  String resp=given().queryParam("key", "qaclick123")
		  .body(a)
		  .when().post("/maps/api/place/add/json")
		  .then().assertThat().statusCode(200).extract().response().asString();
		  
		  
		System.out.println(resp);

			}

		}
		
		
		
		
	
		
		


  
