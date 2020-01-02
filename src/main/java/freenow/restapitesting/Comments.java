package freenow.restapitesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import utils.GetUrl;

import com.darkprograms.speech.translator.GoogleTranslate;

public class Comments {

	static Posts Posts = new Posts();
	
	public  Response getCommentResponse(String user,int loopCount) throws FileNotFoundException, IOException{
		
		String CommentsfromPosts = String.valueOf(Posts.getAllPostIds(user).get(loopCount));
		RequestSpecification httpRequest = GetUrl.getSpecifiedURL("postId", CommentsfromPosts);
		RestAssured.urlEncodingEnabled = true;
		return httpRequest.get("/comments?postId=postId");
	}

	
	public Map<String,String> getCommentbody(String user1) throws FileNotFoundException, IOException{
		
		Map<String, String> PostIdandbody= new HashMap<String, String>();

		for (int comments = 0; comments < Posts.getAllPostIds(user1).size(); comments++) {
			JsonPath js = new JsonPath(getCommentResponse(user1,comments).asString());
			for (int i = 0; i < js.getList("$").size(); i++) {
				String postid = js.getString("postId[" + i + "]");
				String body = js.getString("body[" + i + "]");
				String contentToEnglish = GoogleTranslate.translate("en", body);
				PostIdandbody.put(postid, contentToEnglish);
			}
		}
	
			return PostIdandbody;
}
	
	public List<String> retrieveEmail(String user) throws FileNotFoundException, IOException{
		Map<String, String> PostIdandemail= new HashMap<String, String>();

		List<String> ddd = new ArrayList<String>();
		for (int comments = 0; comments < Posts.getAllPostIds(user).size(); comments++) {
			JsonPath js = new JsonPath(getCommentResponse(user,comments).asString());
			for (int i = 0; i < js.getList("$").size(); i++) {
				String postid = js.getString("postId[" + i + "]");
				String email = js.getString("email[" + i + "]");
				ddd.add(email);
				PostIdandemail.put(postid, email);
				   
				}
			}
		return ddd;
}
}