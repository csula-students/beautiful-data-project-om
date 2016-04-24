package edu.csula.datascience.acquisitionImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.bson.BSONObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.util.JSON;

import org.bson.types.ObjectId;

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
		/* String jsonString =
		 callURL("https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=75000&year=2015",101);
*/
		// https://data.cityofnewyork.us/api/views/nc67-uf89/rows.json?accessType=DOWNLOAD
		// System.out.println(jsonString);
		
		 ReadJsonTest r =new ReadJsonTest();
		 r.getData();
		 
		 System.out.println("done");
		 
	}
	
	
	
	
	
    public void getData(){
    	
    	List<CrimeModel> crime = new ArrayList<CrimeModel>();

    	Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("Crimedata");

		DBCollection collection = db.getCollection("101");

		BasicDBObject query = new BasicDBObject();
		query.put("id", "10000003"); 
		DBCursor cursor = collection.find(query); 
		System.out.println(cursor.count());
		
		while (cursor.hasNext()) {
		    DBObject theObj = cursor.next();
		    
		    System.out.println(theObj.toString());
		    
		    
		    //How to get the DBObject value to ArrayList of Java Object?

		    String name = (String) theObj.get("arrest");
		    System.out.println(name);
		    BasicDBList studentsList =  (BasicDBList) theObj.get("_id");
 		    System.out.println(studentsList.size());
    	    for (int i = 0; i < cursor.size(); i++) {
    	    	BasicDBObject studentObj = (BasicDBObject) studentsList.get(i);
    	    	String case_number = studentObj.getString("case_number");
    	        String district = studentObj.getString("district");
    	        String date = studentObj.getString("date");
    	        String description = studentObj.getString("desscription");

    	        CrimeModel c = new CrimeModel();
    	        c.setCase_number(case_number);
    	        c.setDistrict(district);
    	        c.setDate(date);
    	        c.setDescription(description);

    	        crime.add(c);
    	        
    	        for (CrimeModel cm : crime){
    	        	System.out.println(cm.getDescription());
    	        }
    	    }               
    	}
    
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

			System.out.println("b4 dtabase");

			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("Crimedata");

			DBCollection collection = db.getCollection(Integer.toString(year));

			List<DBObject> a = new ArrayList<DBObject>();

			a = (List<DBObject>) JSON.parse(sb.toString());

			BulkWriteOperation builder = collection
					.initializeUnorderedBulkOperation();
			for (DBObject doc : a) {
				builder.insert(doc);
			}
			com.mongodb.BulkWriteResult result = builder.execute();

			/*
			 * DBObject dbObject = (DBObject) JSON .parse(sb.toString());
			 */

			/*
			 * for (int i=0; i < dbObject.toString().length(); i++) {
			 * collection.insert(dbObject(i)); }
			 */

			System.out.println("data inserted");

			// for printing fetched records
			DBCursor cursorDoc = collection.find();
			while (cursorDoc.hasNext()) {
				System.out.println(cursorDoc.next());
			}

			System.out.println("Done");
			in.close();

		} catch (Exception e) {

			System.out.println("2");
			throw new RuntimeException("Exception while calling URL:" + myURL,
					e);
		}

		return sb.toString();
	}

/*	public static String readFile(String filename) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();

				DBObject dbo = (DBObject) com.mongodb.util.JSON
						.parse('{' + line + '}');
				List<DBObject> list = new ArrayList<>();
				list.add(dbo);
				new MongoClient().getDB("CrimeData").getCollection("rec")
						.insert(list);
			}
			result = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public static void importJSONFileToDBUsingJavaDriver(String pathToFile,
			DB db, String collectionName) {
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
		DBCollection newColl = db.getCollection(collectionName);
		try {
			while ((strLine = br.readLine()) != null) {
				// convert line by line to BSON
				DBObject bson = (DBObject) JSON.parse(strLine);
				// insert BSONs to database
				try {

					newColl.insert(bson);
				} catch (MongoException e) {
					// duplicate key
					e.printStackTrace();
				}

			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}

	}*/

}
