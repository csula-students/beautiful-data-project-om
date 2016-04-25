package edu.csula.datascience.acquisitionImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import edu.csula.datascience.acquisition.CrimeModel;

public class ReadJsonTest {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		// String jsonString =
		// callURL("https://data.cityofchicago.org/resource/6zsd-86xi.json");

		int year = 2001;

		/*for (int i = year; i <= 2015; i++) {
			String jsonString = callURL(
					"https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=100000&year="
							+ i, i);

			System.out.println("============== success for =========== " + i
					+ "============================");
		}*/
		 String jsonString =
		 callURL("https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=10&year=2015",101);
		 
		ReadJsonTest r =new ReadJsonTest();
		//r.getData();
		
		
		
		List<CrimeModel> crimeModelsList=new ArrayList<CrimeModel>();
		crimeModelsList=r.getDataFromAPI("https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=10&year=2015");
		
		r.StoreData(crimeModelsList);
		
		
		// https://data.cityofnewyork.us/api/views/nc67-uf89/rows.json?accessType=DOWNLOAD
		 //System.out.println(jsonString);
		
		
		 
		 System.out.println("done");
		 
	}
	
	
	
	
	
    public void getData() throws JsonParseException, JsonMappingException, IOException{
    	
    	List<CrimeModel> crime = new ArrayList<CrimeModel>();

    	Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("Crimedata");

		DBCollection collection = db.getCollection("101");

		BasicDBObject query = new BasicDBObject();
		query.put("id", "10000003"); 
		DBCursor cursor = collection.find(query); 
		System.out.println(cursor.count());
		
		//Ls
		
		List<CrimeModel> crimeModelList=new ArrayList<CrimeModel>();
		
		while (cursor.hasNext()) {
		    DBObject theObj = cursor.next();
		    
		    System.out.println(theObj.toString());
		    
		    ObjectMapper mapper = new ObjectMapper();

		    CrimeModel obj = mapper.readValue(theObj.toString(), CrimeModel.class);
		    
		    crimeModelList.add(obj);
		    
		    System.out.println(obj.toString());
		    
		}
		System.out.println("crimeModelList size ----->"+crimeModelList.size());
    	}
    
    
    
    
    public List<CrimeModel> getDataFromAPI(String myURL){
    	System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		List <CrimeModel> crimeModelList = new ArrayList<CrimeModel>();

		try {

			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);

					}
					bufferedReader.close();
				}
			}
			
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<DBObject> b= new ArrayList<DBObject>();

			b = (List<DBObject>) JSON.parse(sb.toString());
			
		    
		    for (DBObject doc : b) {
		    	
		    	CrimeModel obj = mapper.readValue(doc.toString(), CrimeModel.class);
			    
			    crimeModelList.add(obj);
			}
		    
		    System.out.println(crimeModelList.size());
		    
		}
		    catch (Exception e) {

				System.out.println("2");
				throw new RuntimeException("Exception while calling URL:" + myURL,
						e);
			}
		    return crimeModelList;
		    
		

    }
    
    

	@SuppressWarnings("unchecked")
	public static String callURL(String myURL, int year) {
		System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {

			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);

					}
					bufferedReader.close();
				}
			}
			
			List <CrimeModel> crimeModelList = new ArrayList<CrimeModel>();
			
			System.out.println("b4 dtabase");

			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("Crimedata");

			DBCollection collection = db.getCollection(Integer.toString(year));

			List<DBObject> a = new ArrayList<DBObject>();

			a = (List<DBObject>) JSON.parse(sb.toString());
			
			System.out.println("===============================================================================");
			System.out.println(sb.toString());

			

			System.out.println("===============================================================================");
			
			
			System.out.println(a.toString());
			BulkWriteOperation builder = collection
					.initializeUnorderedBulkOperation();
			for (DBObject doc : a) {
				
				
				//builder.insert(doc);
			}

			System.out.println("data inserted");

			System.out.println("Done");
			in.close();

		} catch (Exception e) {

			System.out.println("2");
			throw new RuntimeException("Exception while calling URL:" + myURL,
					e);
		}

		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public void StoreData(List<CrimeModel> crimeModelList){
		
	
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("Crimedata");

		DBCollection collection = db.getCollection("100000");

		List<DBObject> a = new ArrayList<DBObject>();

		BulkWriteOperation builder = collection
				.initializeUnorderedBulkOperation();
		
		   Gson gson = new Gson();
		    
		   StringBuilder sb = new StringBuilder();
		    
		    String json = new Gson().toJson(crimeModelList);
		    
		    System.out.println("==============YOYOYO====================================");
		    System.out.println(json);
		    //sb.toString();
		    System.out.println("==================================================");
		    
		    a =  (List<DBObject>) JSON.parse(json);

		
		    for (DBObject doc : a) {
			
		    	builder.insert(doc);
		    }
		    
		    com.mongodb.BulkWriteResult result = builder.execute();
		
	}
	
	


}
