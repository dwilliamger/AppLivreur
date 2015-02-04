package services;

import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONObject;


import android.os.AsyncTask;

public class ServiceWorker extends AsyncTask<Object, Void, Void> {

	ServiceWorkerListener listener;
	String json_result = "";
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		listener.onPrepareRequest();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Void doInBackground(Object... parameters) {
		// TODO Auto-generated method stub
		HTTPRequester jParser = new HTTPRequester();
		String method = (String)parameters[0];
		String url = (String)parameters[1];
		HashMap<String, String> params = (HashMap<String, String>) parameters[3];
		HashMap<String, String> header = (HashMap<String, String>) parameters[2];
		
		JSONObject json = jParser.makeHttpRequest(method, url, header, params);
		json_result = json.toString();
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		listener.onFinishRequestWithJSON(json_result);
	}

}
