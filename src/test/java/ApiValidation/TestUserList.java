package ApiValidation;

import java.io.FileNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import freenow.restapitesting.UserList;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestUserList{ 
	
	public TestUserList() throws FileNotFoundException, IOException{
		// Defeat instantiation
	}
	
	UserList UserList = new UserList();
	Response response = UserList.ApiResponse();
	Map<String, String> Users = UserList.getUserListValues("Samantha");
	JsonPath js = new JsonPath(UserList.ApiResponse().asString());
	
	
	@Test
	public void ValidateStatusCodeforListofUsers() throws FileNotFoundException, IOException {

		Assert.assertEquals( "Status Code is Invalid", 200, response.getStatusCode());

	}

	@Test
	public void ValidateContentType() throws FileNotFoundException, IOException {

		Assert.assertEquals("Invalid Content Type","application/json; charset=utf-8", response.getHeader("Content-Type"));

	}
	
	@Test
	public void ValidateHeaderAuthentication() throws FileNotFoundException, IOException {
		Assert.assertEquals("Invalid HeaderAuthentication","true", response.getHeader("access-control-allow-credentials"));

	}

	@Test
	public void ValidateHeaderexpiry() throws FileNotFoundException, IOException {
		Assert.assertEquals("Invalid Header expiry","-1", response.getHeader("expires"));

	}
	
	@Test
	public void ValidateIdforSamantha() throws FileNotFoundException, IOException {
		Assert.assertTrue("ID for the User Samantha is invalid", Users.get("Samantha").equals("3"));

	}


	@Test
	public void ValidateContentofNestedJSON() throws FileNotFoundException, IOException {
		Map<String, String> company = response.jsonPath().getMap("company[2]");
		Assert.assertEquals("Inner JSON Response is not  valid" , "Romaguera-Jacobson", company.get("name"));

	}

	@Test
	public void ValidateSizeofList() throws FileNotFoundException, IOException {
		Assert.assertSame(10, js.getList("$").size());

	}

	@Test
	public void ValidateJSONParsing() throws FileNotFoundException, IOException {
		for (int i = 0; i > 10; i++) {
			List<String> jsonResponse = response.jsonPath().getList("id[i]");
			Assert.assertEquals(js, jsonResponse);
		}

	}

	@Test
	public void ValidateResponseBodyForSamanthaUser() throws FileNotFoundException, IOException {
		Assert.assertEquals(js.get("name[2]"), "Clementine Bauch");
		Assert.assertEquals(js.get("email[2]"), "Nathan@yesenia.net");
		Assert.assertEquals(js.get("address[2].street"), "Douglas Extension");
		Assert.assertEquals(js.get("address[2].suite"), "Suite 847");
		Assert.assertEquals(js.get("address[2].city"), ("McKenziehaven"));
		Assert.assertEquals(js.get("address[2].zipcode"), ("59590-4157"));
		Assert.assertEquals(js.get("address[2].geo.lat"), ("-68.6102"));
		Assert.assertEquals(js.get("address[2].geo.lng"), ("-47.0653"));
		Assert.assertEquals(js.get("phone[2]"), ("1-463-123-4447"));
		Assert.assertEquals(js.get("website[2]"), ("ramiro.info"));
		Assert.assertEquals(js.get("company[2].name"), ("Romaguera-Jacobson"));
		Assert.assertEquals(js.get("company[2].catchPhrase"), ("Face to face bifurcated interface"));
		Assert.assertEquals(js.get("company[2].bs"), ("e-enable strategic applications"));

	}

}