package io.cloudtype.Demo.module;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestManager {
	
	public static String getResponse(String uri, String data) throws Exception { 
		
		
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line, response = "";
		while ((line = reader.readLine()) != null) {
			response += line + "\n";
		}
		
		return response;
	}

}
