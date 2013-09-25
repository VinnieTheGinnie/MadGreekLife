package com.example.greeklife.transactions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.util.Log;

import com.example.greeklife.HttpConnectionFailedThrowable;
import com.example.greeklife.models.Event;

public class QueryDb  {

	static JSONArray jArray; 
	


	public static ArrayList<String> queryGreekGroups(ArrayList<String> fields, String search) throws HttpConnectionFailedThrowable {
		String result = null; 
		InputStream is = null; 
		StringBuilder sb=null;
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("frat", search));

		//http post


		try{
			HttpClient httpclient = new DefaultHttpClient();
			String url = "http://mobileappdevelopersclub.com/greek_search.php";
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}catch(Exception e){
			Log.e("log_tag", "Error in http connection"+e.toString());
			throw new HttpConnectionFailedThrowable();
		}
		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");

			String line="0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}
		//paring data


		fields = getGreekInfo(result, fields);



		return fields;
	}

	protected static ArrayList<String> getGreekInfo(String result, ArrayList<String> toBeReturned) {
		String id = null;
		String name = null;
		String description= null;
		String address = null;
		String contactNm = null;
		String contactEm = null;
		String tags;
		try{
			jArray = new JSONArray(result);
			JSONObject json_data=null;
			for(int i=0;i<jArray.length();i++){
				json_data = jArray.getJSONObject(i);
				id = json_data.getString("id");
				name=json_data.getString("name");
				description=json_data.getString("description");
				address = json_data.getString("address");
				contactNm = json_data.getString("contact");
				contactEm = json_data.getString("email");
				id = json_data.getString("id");
				tags = json_data.getString("tags");
			}
		}
		catch(JSONException e1){
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}


		toBeReturned.add(id);
		toBeReturned.add(name);
		toBeReturned.add(description);
		toBeReturned.add(address);
		toBeReturned.add(contactNm);
		toBeReturned.add(contactEm);

		return toBeReturned;



	}


	public static ArrayList<Event> queryEvents(Set<String> toSearch) throws HttpConnectionFailedThrowable {
		String result = null; 
		InputStream is = null; 
		StringBuilder sb=null;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


		//http post


		try{
			HttpClient httpclient = new DefaultHttpClient();
			String url = "http://mobileappdevelopersclub.com/event_search.php";
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}catch(Exception e){
			Log.e("log_tag", "Error in http connection"+e.toString());
			throw new HttpConnectionFailedThrowable();
		}
		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");

			String line="0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}
		//paring data

		ArrayList<Event> toReturn = getEventInfo(result, toSearch);



		return toReturn;
	}

	protected static ArrayList<Event> getEventInfo(String result, Set<String> subscribed) {
		String host = null;
		String name = null;
		String description= null;
		String address = null;
		String contactNm = null;
		ArrayList<Event> toReturn = new ArrayList<Event>();

		try{
			jArray = new JSONArray(result);
			JSONObject json_data=null;
			for(int i=0;i<jArray.length();i++){
				json_data = jArray.getJSONObject(i);
				host = json_data.getString("host");
				if(subscribed.contains(host)) {
					name=json_data.getString("name");
					description=json_data.getString("description");
					address = json_data.getString("address");
					contactNm = json_data.getString("contact");
					Event newEvent = new Event(host, name, description, address, contactNm);
					toReturn.add(newEvent);
				}
			}
		}
		catch(JSONException e1){
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}


		return toReturn;



	}


	public static HashMap<String, String> queryAllGroups(HashMap<String, String> fields) throws HttpConnectionFailedThrowable {
		String result = null; 
		InputStream is = null; 
		StringBuilder sb=null;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


		//http post


		try{
			HttpClient httpclient = new DefaultHttpClient();
			String url = "http://mobileappdevelopersclub.com/all_search.php";
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}catch(Exception e){
			Log.e("log_tag", "Error in http connection"+e.toString());
			throw new HttpConnectionFailedThrowable();
		}
		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");

			String line="0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}
		//paring data


		fields = getSearchInfo(result, fields);



		return fields;
	}

	protected static HashMap<String, String> getSearchInfo(String result, HashMap<String, String> toBeReturned) {
		String name = null;
		String tags = null;

		try{
			jArray = new JSONArray(result);
			JSONObject json_data=null;
			for(int i=0;i<jArray.length();i++){
				json_data = jArray.getJSONObject(i);
				name=json_data.getString("name");
				tags = json_data.getString("tags");
				toBeReturned.put(name, tags);

			}
		}
		catch(JSONException e1){
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return toBeReturned;



	}
	
	/*
	 * Eventually will be used to get the image for the respective frat/sorotity
	 * 
	 * from example at 
	 * 
	 * http://www.java-tips.org/other-api-tips/jdbc/how-to-store-retrieve-image-to-from-sqlserver-2.html
	 * 
	 *  DB db = new DB();
                Connection conn=db.dbConnect(
                  "jdbc:jtds:sqlserver://localhost:1433/test","sa","");
                db.insertImage(conn,"d://filepath//test.JPG");
                db.getImageData(conn);
	 * 
	public void getImageData(Connection conn)
    {
            
             byte[] fileBytes;
             String query;
             try
             {
                     query = "select data from tableimage";
                     Statement state = conn.createStatement();
                     ResultSet rs = state.executeQuery(query);
                     if (rs.next())
                    {
                              fileBytes = rs.getBytes(1);
                              OutputStream targetFile = new FileOutputStream("d://filepath//new.JPG");
                              targetFile.write(fileBytes);
                              targetFile.close();
                    }        
                    
             }
             catch (Exception e)
             {
                     e.printStackTrace();
             }
    }
    
    */


}
