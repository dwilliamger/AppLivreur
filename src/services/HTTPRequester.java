package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class HTTPRequester {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public HTTPRequester() {

	}
	
	public static List<NameValuePair> nameValuePairFromHashMap (HashMap<String, String> header){
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Set set = header.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			params.add(new BasicNameValuePair(me.getKey().toString(), me.getValue().toString()));
			
		}
		
		return params;
	}
	// function get json from url
	// by making HTTP POST or GET mehtod
	public JSONObject makeHttpRequest(String method, String url,
			HashMap<String, String> header, HashMap<String, String> params) {

		// Making HTTP request
		try {
			
			// check for request method
			if(method == "POST"){
				// request method is POST
				// defaultHttpClient
				DefaultHttpClient httpClient = new DefaultHttpClient();
			
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairFromHashMap(params)));
				
				Set set = header.entrySet();
				Iterator i = set.iterator();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					httpPost.addHeader(me.getKey().toString(), me.getValue().toString());
					
				}

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
				
			}else if(method == "GET"){
				// request method is GET
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String paramString = URLEncodedUtils.format(nameValuePairFromHashMap(params), "utf-8");
				url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);
				
				Set set = header.entrySet();
				Iterator i = set.iterator();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					httpGet.addHeader(me.getKey().toString(), me.getValue().toString());
					
				}


				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}
			else 
				if(method == "PUT"){
					// request method is POST
					// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();
				
					HttpPut httpPut = new HttpPut(url);
					httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairFromHashMap(params)));
					
					Set set = header.entrySet();
					Iterator i = set.iterator();
					while (i.hasNext()) {
						Map.Entry me = (Map.Entry)i.next();
						httpPut.addHeader(me.getKey().toString(), me.getValue().toString());
						
					}

					HttpResponse httpResponse = httpClient.execute(httpPut);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
					
				}
			
				else if(method == "DELETE"){
					
					
				}
			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
}
