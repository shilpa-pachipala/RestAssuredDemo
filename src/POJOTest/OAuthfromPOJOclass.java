package POJOTest;
import static  io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class OAuthfromPOJOclass {
	public static void main(String[] args) {
		
		//declare array of Strings
		String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"};
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
	
	
	
	GetCourse gc=given().queryParam("access_token",accesstoken )
	.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
	.as(GetCourse.class);
	System.out.println(gc.getLinkedIn());
	System.out.println(gc.getInstructor());
	System.out.println(gc.getExpertise());
	//print course title of Api's node 2 childnd name
	System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
	System.out.println(gc.getCourses().getWebAutomation().get(2).getPrice());
	
	//1st step goto Api node, then for loop to iterate the
	//loop to get tiltle= soap/rest then get the course price by
	//same path bt insted tile price weshould select
	List<Api> apicourse=gc.getCourses().getApi();
	for(int i=0;i<apicourse.size();i++)
	{
		if(apicourse.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))
		{
			System.out.println(apicourse.get(i).getPrice());
		}
	}
		
	
	//print course titles of WebAutomation
	
	List<WebAutomation>webtitles=gc.getCourses().getWebAutomation();
	for(int i=0;i<webtitles.size();i++)
	{
		System.out.println(webtitles.get(i).getCourseTitle());
	}

	
	//Declare array with course names and compare them with json resp
	ArrayList<String>a=new ArrayList<String>();
	
	
	List<WebAutomation>WebT=gc.getCourses().getWebAutomation();
	for(int p=0;p<WebT.size();p++)
	{
		a.add(WebT.get(p).getCourseTitle());
	}
		
	
	List<String>ExpectedList=Arrays.asList(courseTitles);
		
		Assert.assertTrue(WebT.equals(ExpectedList));
	}
	

}