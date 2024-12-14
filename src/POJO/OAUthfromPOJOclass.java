package POJO;
import static  io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class OAUthfromPOJOclass {
	public static void main(String[] args) {
		
		String response=given().log().all()
		.formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString()
;		
		
		JsonPath js=new JsonPath(response);
	String accesstoken=js.get("access_token");
	System.out.println(accesstoken);
	
	//to parse through Json
	
	 /*String resp=given().queryParam("access_token",accesstoken)
	.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
	.asString();
	System.out.println(resp);
	
	JsonPath js1=new JsonPath(resp);
	String linkedin=js1.getString("linkedIn");
	String Ins=js1.getString("instructor");
	System.out.println(Ins); */
	
	/*To parse json resp from POJO class we have to first add
	pojo classes for all the variables in resp and sub child/nested
	variables all then parse like below instead Json*/
	
	GetCourse gc=given().queryParam("access_token",accesstoken )
	.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
	.as(GetCourse.class);
	System.out.println(gc.getLinkedIn());
	System.out.println(gc.getCourses());
	
	
	
	
	
		
		
	}
	

}
