package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


public class Services {
	
	public static void sendRequest(String methode, String url, HashMap<String, String> header, HashMap<String, String> params, ServiceWorkerListener listener) {
		ServiceWorker worker = new ServiceWorker();
		worker.listener = listener;
		worker.execute(methode, url, header, params);
	}

}
