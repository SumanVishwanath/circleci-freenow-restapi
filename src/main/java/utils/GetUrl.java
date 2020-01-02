package utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.ConfigUtility;

public final class GetUrl {

	public GetUrl(){
		//Defeat instantiation
	}
	
	
	
	public static RequestSpecification getSpecifiedURL(String id,String path) throws FileNotFoundException, IOException{
		return RestAssured.given().baseUri(ConfigUtility.getURL())
				.contentType(ContentType.URLENC.withCharset("UTF-8")).formParam(id, path);
	}
	
	public static RequestSpecification getBaseUrl() throws FileNotFoundException, IOException{
			return RestAssured.given().baseUri(ConfigUtility.getURL());
	}
	
}
