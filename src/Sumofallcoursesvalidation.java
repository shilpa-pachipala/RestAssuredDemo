import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Sumofallcoursesvalidation {
	
	@Test
	public void sumofcourses()
	{
	JsonPath js=new JsonPath(payload.courseprice());
	int count=js.getInt("courses.size()");
	
	
	for(int i=0;i<count;i++)
	{
		int price=js.getInt("courses["+i+"].price");
		int copies=js.get("courses["+i+"].copies");
		int amount=price * copies;
		System.out.println(amount);
	}
	}
	
	

}
