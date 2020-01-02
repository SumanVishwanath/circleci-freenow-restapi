package ApiValidation;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import freenow.restapitesting.UserList;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class UserListValidations {

	@Test
	public void ValidateStatusCodeforListofUsers() {

		Response response = UserList.ApiResponse();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Status Code is Invalid");

	}

	@Test
	public void ValidateContentType() {

		Response response = UserList.ApiResponse();
		Assert.assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"),
				"Invalid Content Type");

	}
	
	@Test
	public void ValidateHeaderAuthentication() {

		Response response = UserList.ApiResponse();
		Assert.assertEquals("true", response.getHeader("access-control-allow-credentials"),
				"Invalid HeaderAuthentication");

	}

	@Test
	public void ValidateHeaderexpiry() {

		Response response = UserList.ApiResponse();
		Assert.assertEquals("-1", response.getHeader("expires"),
				"Invalid Header expiry");

	}
	
	@Test
	public void ValidateIdforSamantha() {

		Map<String, String> Users = UserList.UserLists("Samantha");
		Assert.assertTrue(Users.get("Samantha").equals("3"), "ID for the User Samantha is invalid");

	}

	@Test
	public void ValidateUsersList() {

		Response response = UserList.ApiResponse();
		ResponseBody json = response.getBody();
		Assert.assertSame(response, json, "User List is not valid");

	}

	@Test
	public void ValidateContentofNestedJSON() {

		Response response = UserList.ApiResponse();
		Map<String, String> company = response.jsonPath().getMap("company[2]");
		Assert.assertEquals("Romaguera-Jacobson", company.get("name"), "Inner JSON Response is not  valid");

	}

	@Test
	public void ValidateSizeofList() {

		Response response = UserList.ApiResponse();
		String key = response.asString();
		List<String> jsonResponse = response.jsonPath().getList("$");
		Assert.assertSame(10, jsonResponse.size(), "Size of the JSON is not valid");

	}

	@Test
	public void ValidateJSONParsing() {

		Response response = UserList.ApiResponse();
		String key = response.asString();
		for (int i = 0; i > 10; i++) {
			List<String> jsonResponse = response.jsonPath().getList("id[i]");
			Assert.assertEquals(key, jsonResponse, "Parsing Response is not valid");
		}

	}

	@Test
	public void ValidateResponseBodyForSamanthaUser() {

		Response response = UserList.ApiResponse();
		String key = response.asString();
		JsonPath js = new JsonPath(key);
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