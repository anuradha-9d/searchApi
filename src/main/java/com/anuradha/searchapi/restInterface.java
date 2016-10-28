package com.anuradha.searchapi;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface restInterface {
	
	public String getResponse() throws ClientProtocolException, IOException;
	public int getStatus();

}
