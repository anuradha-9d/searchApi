package com.anuradha.searchapi;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class searchApiTest {
	
	@Test
	//Validate resultscount is 50 by default
	public void test_resultcount() throws ClientProtocolException, IOException, ParseException{
		String URL = "https://itunes.apple.com/search?term=jackson";
		restClient rc = new restClient(URL);
		String response = rc.getResponse();
		searchApiParser parser = new searchApiParser(response);
		Assert.assertEquals(parser.getResultCount(),50);	
		rc.close();
	}
	
	@Test
	//Valid basic http status when term=jackson
	public void test_status() throws ClientProtocolException, IOException, ParseException{
		String URL = "https://itunes.apple.com/search?term=jackson";
		restClient rc = new restClient(URL);
		Assert.assertEquals(rc.getStatus(),200);	
		rc.close();
	}
	
	@Test
	//Validate resultscount is matching the actual results object size.
	public void test_results_size() throws ClientProtocolException, IOException, ParseException{
		String URL = "https://itunes.apple.com/search?term=jackson";
		restClient rc = new restClient(URL);
		String response = rc.getResponse();
		searchApiParser parser = new searchApiParser(response);
		Assert.assertEquals(parser.getResults().size(),50);	
		rc.close();
	}
	
	@Test
	//Check for valid country (eg:US)
	public void test_term_country() throws ClientProtocolException, IOException, ParseException{
		String URL = "https://itunes.apple.com/search?term=jackson&country=US";
		restClient rc = new restClient(URL);
		Assert.assertEquals(rc.getStatus(),200);	
		rc.close();
	}
	
	@Test
	//Check for http 400 bad request when country is invalid (eg: USSR)
	public void test_term_country_invalid() throws ClientProtocolException, IOException, ParseException{
		String URL = "https://itunes.apple.com/search?term=jackson&country=USSR";
		restClient rc = new restClient(URL);
		Assert.assertEquals(rc.getStatus(),400);	
		rc.close();
	}
	
	@Test
	//Check for valid http status when media=podcast
	public void test_term_media() throws ClientProtocolException, IOException, ParseException{
		String URL = "https://itunes.apple.com/search?term=jackson&media=podcast";
		restClient rc = new restClient(URL);
		Assert.assertEquals(rc.getStatus(),200);	
		rc.close();
	}
	
	@Test
	//Check for invalid media (eg:video). It should give http 400 bad request
	public void test_term_media_invalid() throws ClientProtocolException, IOException, ParseException{
		//video is invalid media
		String URL = "https://itunes.apple.com/search?term=jackson&media=video";
		restClient rc = new restClient(URL);
		Assert.assertEquals(rc.getStatus(),400);	
		rc.close();
	}
	
	@Test
	//Check for limit parameter
	public void test_term_limit_resultcount() throws ClientProtocolException, IOException, ParseException{
		String URL = "https://itunes.apple.com/search?term=jackson&limit=40";
		restClient rc = new restClient(URL);
		String response = rc.getResponse();
		searchApiParser parser = new searchApiParser(response);
		Assert.assertEquals(parser.getResultCount(),40);	
		rc.close();
	}
	
	@Test
	//Check the max value of limit parameter (it should be 200)
	public void test_term_limit_max_resultcount() throws ClientProtocolException, IOException, ParseException{
		String URL = "https://itunes.apple.com/search?term=jackson&limit=400";
		restClient rc = new restClient(URL);
		String response = rc.getResponse();
		searchApiParser parser = new searchApiParser(response);
		Assert.assertEquals(parser.getResultCount(),200);	
		rc.close();
	}
}
	
	
	
	
