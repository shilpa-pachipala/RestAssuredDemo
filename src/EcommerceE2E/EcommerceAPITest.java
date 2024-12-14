package EcommerceE2E;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.LoginResponse;

import POJO.OrderDetail;

import POJO.Orders;
import POJO.loginreq;


public class EcommerceAPITest {

	public static void main(String[] args) {
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.setContentType(ContentType.JSON).build();
		loginreq loginrequest=new loginreq();
		loginrequest.setUserEmail("lakku@gmail.com");
		loginrequest.setUserPassword("Lakku@123");
		
		
		
		RequestSpecification login=given().spec(req).body(loginrequest);
		
		LoginResponse response=login.when().post("/api/ecom/auth/login").then().log().all()
				.extract().response().as(LoginResponse.class);
		System.out.println(response.getMessage());
		System.out.println(response.getUserId());
		String userid=response.getUserId();
		System.out.println(response.getToken());
		
		String token=response.getToken();
		System.out.println(token);
		
		
		//Add product
		
				RequestSpecification Addprod=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
						.addHeader("Authorization",token).build();
				
				RequestSpecification Addproduct=given().log().all().spec(Addprod).param("productName","apitest123")
						.param("productAddedBy", "userid").param("productCategory","fashion")
						.param("productSubCategory","shirts").param("productPrice","11500")
						.param("productDescription","Addias Originals")
						.params("productFor","women")
						.multiPart("productImage",new File("C://Users//srava//Documents//laptop.jpg"));
				
				String Addprodresponse=Addproduct.when().post("/api/ecom/product/add-product").then().log().all().extract()
				.response().asString();
				
				JsonPath js=new JsonPath(Addprodresponse);
				String ProductId=js.getString("productId");
				System.out.println(ProductId);
				System.out.println( js.getString("message"));
				
				//create order
				
				RequestSpecification ordr=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
						.addHeader("Authorization",token).build();
				
			
				OrderDetail orderdetail=new OrderDetail();
				orderdetail.setCountry("India");
				orderdetail.setProductOrderedId(ProductId);
				
				List<OrderDetail>orderd=new ArrayList<OrderDetail>();
				orderd.add(orderdetail);
				
				Orders order=new Orders();
				order.setOrders(orderd);
				
				RequestSpecification req1=given().log().all().spec(ordr).body(order);
			String	Response =req1.when().post("/api/ecom/order/create-order")
					.then().log().all().extract().asString();
			
			System.out.println(Response); 
			
			//delete product
			
			
				
			RequestSpecification delete=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
					.addHeader("Authorization",token).build();
			RequestSpecification deleteprod	=given().log().all().spec(delete).pathParam("productId",ProductId);
			
			String deleteresp=deleteprod.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all()
			.extract().response().asString();
			JsonPath js3=new JsonPath(deleteresp);
			String msg=js.get("message");
			//Assert.assertEquals("Product Deleted Successfully",msg);
			
			
			
				
				
				
				
				
				
		
				
		
		
		
		
		
		
		
						}

}
