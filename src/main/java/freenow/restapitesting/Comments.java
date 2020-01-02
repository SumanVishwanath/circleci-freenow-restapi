package freenow.restapitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.darkprograms.speech.translator.GoogleTranslate;

public class comments {

	public static String Comment() throws IOException {
		String Test = null;

		for (int x = 0; x < Posts.postjsonSize; x++) {
//list
			String CommentsfromPosts = String.valueOf(Posts.postComment.get(x));

			RequestSpecification httpRequest = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com")
					.contentType(ContentType.URLENC.withCharset("UTF-8")).formParam("postId", CommentsfromPosts);
			RestAssured.urlEncodingEnabled = true;
			Response GETRESPONSE = httpRequest.get("/comments?postId=postId");
			String key = GETRESPONSE.asString();
			JsonPath js = new JsonPath(key);

			List<String> jsonResponse = js.getList("$");
			int JSONSize = jsonResponse.size();
			System.out.println("jsonSize" + JSONSize);

			Map<String, String> jsonResponse1 = new HashMap();

			for (int i = 0; i < JSONSize; i++) {
				String postId = js.getString("postId[" + i + "]");
				String email = js.getString("email[" + i + "]");
				jsonResponse1.put(postId, email);
				System.out.println("postId and EmailDetails" + jsonResponse1);

			}

			Map<String, String> jsonResponse2 = new HashMap();

			for (int i = 0; i < JSONSize; i++) {
				String postid = js.getString("postId[" + i + "]");
				String commentbody = js.getString("body[" + i + "]");
				String contentToEnglish = GoogleTranslate.translate("en", commentbody);
				jsonResponse2.put(postid, contentToEnglish);
				System.out.println("PostId and Comments" + jsonResponse2);

			}
			Map<String, String> jsonResponse3 = new HashMap();

			for (int i = 0; i < JSONSize; i++) {
				String postid = js.getString("postId[" + i + "]");
				String id = js.getString("id[" + i + "]");
				jsonResponse3.put(postid, id);
				System.out.println("PostId and Id of Comments" + jsonResponse3);

			}

		}
		return Test;
	}
}
