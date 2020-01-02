package ApiValidation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import freenow.restapitesting.Comments;
import freenow.restapitesting.Posts;
import freenow.restapitesting.UserList;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class TestComments{ 
	
	public TestComments() throws FileNotFoundException, IOException{
		// Defeat instantiation
	}
	
	UserList UserList = new UserList();
	Posts Posts = new Posts();
	Comments Comments = new Comments();
	
	
	Response response = Comments.getCommentResponse("Samantha",1);
	int GetPostIdsforallcomments = Posts.getAllPostIds("Samantha").size();
	JsonPath js = new JsonPath(Comments.getCommentResponse("Samantha",1).asString());
	List<String> EmailisValid = Comments.retrieveEmail("Samantha");
	Pattern pattern = Pattern.compile("^([\\w]((\\.(?!\\.))|[-!#\\$%'\\*\\+/=\\?\\^`\\{\\}\\|~\\w])*)(?<=[\\w])@(([\\w][-\\w]*[\\w]\\.)+[a-zA-Z]{2,6})$", Pattern.CASE_INSENSITIVE);
	
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
	public void ValidateEmailFormatforSamantha() throws FileNotFoundException, IOException {	
		List<String>emails= Comments.retrieveEmail("Samantha");
		for(int i=0;i<emails.size();i++){
	        Matcher matcher= pattern.matcher(emails.get(i));
	        Assert.assertEquals(matcher.matches(), true);
		}
	}
	
	@Test
	public void ValidateInvalidEmailFormat() throws FileNotFoundException, IOException {	
	        Matcher matcher= pattern.matcher("Lura@rod.tv,jk");
	        Assert.assertEquals(matcher.matches(), false);
		}
	}
		

