package freenow.restapitesting;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.GetUrl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ConfigUtility;

public class Posts {

	 UserList userList = new UserList();
	 
	
	public Response getPostResponse(String user) throws FileNotFoundException, IOException{
		String userId= userList.getUserListValues(user).get(user);
		RequestSpecification httpRequest = GetUrl.getSpecifiedURL("userId",userId).urlEncodingEnabled(true);
		return httpRequest.get(ConfigUtility.getPostPath()+userId);
	}

	public  List<String> getAllPostIds(String user) throws IOException {
		JsonPath js = new JsonPath(getPostResponse(user).asString());
		return js.getList("id");

	}
	
	public  Map<String,String> getUserIDAndPostID(String user) throws FileNotFoundException, IOException{
		JsonPath js = new JsonPath(getPostResponse(user).asString());
		Map<String,String> userIdAndpostID = new HashMap<String,String>();
		for (int i = 0; i < js.getList("$").size(); i++) {
			String userId = js.getString("userId[" + i + "]");
			String postid = js.getString("id[" + i + "]");
			userIdAndpostID.put(userId, postid);
		}		
		return userIdAndpostID;
	}
}
