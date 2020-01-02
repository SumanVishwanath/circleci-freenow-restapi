package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigUtility {
	
	
	private static String loadPropertyFileValues(String key) throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		try{
			InputStream input = new FileInputStream("src\\main\\resources\\config.properties");
			prop.load(input);
			return prop.getProperty(key);
		}
		finally{}
	}

	
	public static String getURL() throws FileNotFoundException, IOException{
		return loadPropertyFileValues("testUrl");
	}
	
	public static String getUserPath() throws FileNotFoundException, IOException{
		return loadPropertyFileValues("userPath");
	}
	
	public static String getPostPath() throws FileNotFoundException, IOException{
		return loadPropertyFileValues("postPath");
	}

}
