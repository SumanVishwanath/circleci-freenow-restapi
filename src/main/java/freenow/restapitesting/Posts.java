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

public class Posts {

	public static String postid1;
	public static int postjsonSize;
	public static List<String> postComment;

	public static List<String> Post() throws IOException {

	

		RequestSpecification httpRequest = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com")
				.contentType(ContentType.URLENC.withCharset("UTF-8")).formParam("userId", UserList.postuser);
		RestAssured.urlEncodingEnabled = true;
		Response GETRESPONSE = httpRequest.get("/posts?userId=userId");
		String key = GETRESPONSE.asString();
		JsonPath js = new JsonPath(key);

		List<String> jsonResponse = js.getList("$");
		postjsonSize = jsonResponse.size();
		System.out.println("jsonSize" + postjsonSize);

		Map<String, String> jsonResponse1 = new HashMap();

		postComment = js.getList("id");

		for (int i = 0; i < postjsonSize; i++) {
			String userId = js.getString("userId[" + i + "]");
			postid1 = js.getString("id[" + i + "]");

			jsonResponse1.put(userId, postid1);

			System.out.println("UserName and Id Map" + jsonResponse1);

		}

		return postComment;

	}
}
