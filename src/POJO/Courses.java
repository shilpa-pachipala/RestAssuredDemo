package POJO;

import java.util.List;

public class Courses {
	private List<WebAutomation> webAutomation;
	private List<Api> api;
	private List<Mobile> mobile;
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
	
	
	
//now we need to inject this courses into parent pojo
	//classe and return type of the parent should not
	//be string it should be this Course bcz from this
	// object all the child json info will be injected 
	//change parent courses to Course
}
