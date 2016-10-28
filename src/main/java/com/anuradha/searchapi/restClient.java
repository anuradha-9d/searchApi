package com.anuradha.searchapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class restClient implements restInterface {
	
	String URL;
	CloseableHttpClient httpclient;
	CloseableHttpResponse response1;
	
	public restClient(String URL) throws ClientProtocolException, IOException{
		this.URL = URL;
		this.invoke();
	}
	
	private void invoke() throws ClientProtocolException, IOException {
		this.httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(this.URL);
		this.response1 = httpclient.execute(httpGet);
		System.out.println(response1);	
	}
	
	public String getResponse() throws ClientProtocolException, IOException{
		HttpEntity entity1 = this.response1.getEntity();
		String response = EntityUtils.toString(entity1);
		return response;
	}
	
	public int getStatus(){
		int status_code = response1.getStatusLine().getStatusCode();
		return status_code;
		
	}
	
	public void close() throws IOException{
		this.response1.close();
		this.httpclient.close();
		
	}
}