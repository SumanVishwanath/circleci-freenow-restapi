package ApiValidation;

import java.io.FileNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.darkprograms.speech.translator.GoogleTranslate;

import freenow.restapitesting.Posts;
import freenow.restapitesting.UserList;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestPosts{ 
	
	public TestPosts() throws FileNotFoundException, IOException{
		// Defeat instantiation
	}
	Posts Posts = new Posts();
	
	UserList UserList = new UserList();
	Posts Posting = new Posts();
	Response response = Posts.getPostResponse("");
	String UserId = UserList.getUserListValues("Samantha").get("Samantha");
	Map<String, String> UserIdandPostId = Posts.getUserIDAndPostID("");
	JsonPath js = new JsonPath(Posts.getPostResponse("Samantha").asString());
	List<String> getAllPostIdsforSamantha = Posts.getAllPostIds("Samantha");
	String title = js.getString("title[0]");
	String Translatedtitle = GoogleTranslate.translate("en", title);
	
	
	@Test
	public void ValidateStatusCodeforPosts() throws FileNotFoundException, IOException {

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
	public void ValidatepostIdforSamantha() throws FileNotFoundException, IOException {
		for (int i = 0; i < js.getList("$").size(); i++) {
		Assert.assertTrue(js.getList("id").equals(getAllPostIdsforSamantha));
		}

	}


	@Test
	public void ValidateContentforUserID() throws FileNotFoundException, IOException {
	     Assert.assertEquals("UserId is not valid for Samantha User","3",UserId );

	}

	@Test
	public void ValidateSizeofList() throws FileNotFoundException, IOException {
		Assert.assertSame(10, js.getList("$").size());

	}

	@Test
	public void ValidateJSONParsing() throws FileNotFoundException, IOException {
		for (int i = 0; i > 10; i++) {
			List<String> jsonResponse = response.jsonPath().getList("ie[i]");
			Assert.assertEquals(js, jsonResponse);
		}
	}
	
	
	@Test
	public void ValidateReponseStructureofPost() throws FileNotFoundException, IOException {
		Assert.assertEquals(js.getString("userId[0]"), "3");
		Assert.assertEquals(js.getString("id[0]"), "21");
		Assert.assertEquals(Translatedtitle, "Because of the extremely small ways Tungsten");
	}
}