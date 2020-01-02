package freenow.restapitesting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserList {
	public static String postuser, DefinedUsername;
	public static int UserListJSONSize;

	public static List<String> Users;

	public UserList() {
		// Defeat instantiation
	}

	public static Map<String, String> UserLists(String DefinedUsername) {

		Response resp = ApiResponse();
		Map<String, String> MapOfUsers = getUserListValues(resp);
		return MapOfUsers;

	}

	public static Response ApiResponse() {

		RequestSpecification httpRequest = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com");
		Response GETRESPONSE = httpRequest.get("/users");
		return GETRESPONSE;
	}

	public static Map<String, String> getUserListValues(Response GETRESPONSE) {
		String key = GETRESPONSE.asString();
		JsonPath js = new JsonPath(key);

		List<String> jsonResponse = js.getList("$");
		UserListJSONSize = jsonResponse.size();

		Map<String, String> jsonResponse1 = new HashMap();

		for (int i = 0; i < UserListJSONSize; i++) {
			String Username = js.getString("username[" + i + "]");
			String id = js.getString("id[" + i + "]");
			// Users = js.getList("username");
			// String AllUsers = String.valueOf(Users.get(i));
			DefinedUsername = "Samantha";
			if (Username.contains(DefinedUsername)) {
				jsonResponse1.put(Username, id);
				postuser = id;
	           System.out.println("UserName and Id Map" + jsonResponse1 + "\n" + "Value of id for entered User is " + postuser);
			}

		}
		return jsonResponse1;
	}
}
