package com.swaglabtest.testcases;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkValidator {
	public static int validateLink(String url) {

		int responseCode = 0;

		try {

			URL link = new URL(url);

			HttpURLConnection connection = (HttpURLConnection) link.openConnection();

			connection.setRequestMethod("HEAD");

			connection.connect();

			responseCode = connection.getResponseCode();

		} catch (Exception e) {

			System.out.println("Invalid URL : " + url);
		}

		return responseCode;
	}

}
