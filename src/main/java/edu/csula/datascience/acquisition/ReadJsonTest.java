package edu.csula.datascience.acquisition;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class ReadJsonTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
	
		//String jsonString = callURL("https://data.cityofchicago.org/resource/6zsd-86xi.json");
	    
		int year=2012;
		
		for (int i=year;i<=2014;i++ ){
		//String jsonString = callURL("https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=100000&year="+i,i);
	
		String jsonString = callURL("https://data.cityofboston.gov/resource/ufcx-3fdn.json?$limit=100000&year="+i,i);
		
		//https://data.cityofboston.gov/resource/ufcx-3fdn.json?year=2014

		System.out.println("============== success for =========== " + i + "============================");
		}
		//String jsonString = callURL("https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=75000&year=2015",2015);

		//https://data.cityofnewyork.us/api/views/nc67-uf89/rows.json?accessType=DOWNLOAD
		//System.out.println(jsonString);
		
	}
	
@SuppressWarnings("unchecked")
public static String callURL(String myURL,int year) {
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
		
		System.out.println("b4 dtabase");
		
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("Crimedata");
		
		DBCollection collection = db.getCollection(Integer.toString(year)+"_boston");

		List<DBObject> a = new ArrayList<DBObject>();
		
		a = (List<DBObject>) JSON
				.parse(sb.toString());
		
		BulkWriteOperation builder = collection.initializeUnorderedBulkOperation();
        for(DBObject doc : a)
        {
            builder.insert(doc);
        }
        com.mongodb.BulkWriteResult result = builder.execute();
        
		
		/*DBObject dbObject = (DBObject) JSON
				.parse(sb.toString());*/

		/*for (int i=0; i < dbObject.toString().length(); i++) {
			collection.insert(dbObject(i));
		}*/
		
		System.out.println("data inserted");

		DBCursor cursorDoc = collection.find();
		while (cursorDoc.hasNext()) {
			System.out.println(cursorDoc.next());
		}

		System.out.println("Done");	
	    in.close();
	} catch (Exception e) {

		System.out.println("2");
		throw new RuntimeException("Exception while calling URL:"+ myURL, e);
	}


	return sb.toString();
}

	public static String readFile(String filename) {
	    String result = "";
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(filename));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();

	            DBObject dbo = (DBObject) com.mongodb.util.JSON.parse('{'+line+'}');
	            List<DBObject> list = new ArrayList<>();
	            list.add(dbo);
	            new MongoClient().getDB("CrimeData").getCollection("rec").insert(list);
	        }
	        result = sb.toString();
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	
	    return result;
	    
	}
	
	
	public static void importJSONFileToDBUsingJavaDriver(String pathToFile, DB db, String collectionName) {
	    // open file
	    FileInputStream fstream = null;
	    try {
	        fstream = new FileInputStream(pathToFile);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("file not exist, exiting");
	        return;
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	    // read it line by line
	    String strLine;
	    DBCollection newColl =   db.getCollection(collectionName);
	    try {
	        while ((strLine = br.readLine()) != null) {
	            // convert line by line to BSON
	            DBObject bson = (DBObject) JSON.parse(strLine);
	            // insert BSONs to database
	            try {
	            	
	                newColl.insert(bson);
	            }
	            catch (MongoException e) {
	              // duplicate key
	              e.printStackTrace();
	            }


	        }
	        br.close();
	    } catch (IOException e) {
	        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	    }


	}
	
}
