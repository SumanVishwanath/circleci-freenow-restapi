package freenow.restapitesting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigUtility;
import utils.GetUrl;


public class UserList {

	public UserList() {
		// Defeat instantiation
	}

	public Response ApiResponse() throws FileNotFoundException, IOException {
		RequestSpecification httpRequest = GetUrl.getBaseUrl();
		return httpRequest.get(ConfigUtility.getUserPath());

	}

	public Map<String, String> getUserListValues(String definedUsername1) throws FileNotFoundException, IOException {
		Map<String, String> userNameAndID = new HashMap<String, String>();
		JsonPath js = new JsonPath(ApiResponse().asString());

		for (int i = 0; i < js.getList("$").size(); i++) {
			String Username = js.getString("username[" + i + "]");
			if (Username.contains(definedUsername1)) {
				String id = js.getString("id[" + i + "]");
				userNameAndID.put(Username, id);
			}

		}
		return userNameAndID;
	}

}
