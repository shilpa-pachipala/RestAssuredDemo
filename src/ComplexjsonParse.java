import io.restassured.path.json.JsonPath;

public class ComplexjsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    JsonPath js=new JsonPath(payload.courseprice());
    //number of courses
   int coursecount =js.getInt("courses.size()");
   System.out.println(coursecount);
   //purchase amount
     int totalamount=js.getInt("dashboard.purchaseAmount");
     System.out.println(totalamount);
     
     //title of 1st course
     
    String firsttitle= js.getString("courses[0].title");
    System.out.println(firsttitle);
     
     
     String thirdtitle =js.getString("courses[2].title");
     System.out.println(thirdtitle);
     
     //read all titles & prices
     
     for(int i=0;i<coursecount;i++)
     {
    	String titles= js.getString("courses["+i+"].title");
    	System.out.println(titles);
    	
    	
    	int prices=js.getInt("courses["+i+"].price");
    	System.out.println(prices);
    	
     }
     //no of course sold by RPA
     System.out.println("num of courses sold be RPA");
     for(int i=0;i<coursecount;i++)
     {
    	String coursetitle =js.get("courses["+i+"].title");
    	if(coursetitle.equalsIgnoreCase("RPA"))
    	{
    		int copies=js.get("courses["+i+"].copies");
    		System.out.println(copies);
    	}
    	
    	
    	
    	
     }
     
     
     
     
     
     
     
     
     
     
	}

}
