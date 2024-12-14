package OAuthTest;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;


public class ClientcredentialsTest {

	public static void main(String[] args) {
		String response=given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String accesstoken=js.getString("access_token");
		System.out.println(accesstoken);
		
		String response1=given()
		.queryParam("access_token", accesstoken)
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		
		System.out.println(response1);

		JsonPath js1=new JsonPath(response1);
		String instructor=js1.getString("instructor");
		System.out.println(instructor);
	}

}
