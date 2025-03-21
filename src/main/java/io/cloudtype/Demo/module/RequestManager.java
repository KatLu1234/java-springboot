package io.cloudtype.Demo.module;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class RequestManager {
	
	static {
		TrustManager[] trustAllCertificates = new TrustManager[] {
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					
					public void checkClientTrusted(X509Certificate[] certs, String authType) { }
					public void checkServerTrusted(X509Certificate[] certs, String authType) { }
			
				}
		};
		
		try {
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, trustAllCertificates, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getResponse(String uri, String data) throws Exception { 
		
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line, response = "";
		while ((line = reader.readLine()) != null) {
			response += line + "\n";
		}
		
		reader.close();
		connection.disconnect();
		
		return response.trim();
	}
	
	

}
