package edu.csula.datascience.acquisitionImplTest;

import java.util.ArrayList;
import java.util.List;

import org.bson.BSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import edu.csula.datascience.acquisition.Collector;
import edu.csula.datascience.acquisition.CrimeModel;
import edu.csula.datascience.acquisition.MockCollector;
import edu.csula.datascience.acquisition.M;
import edu.csula.datascience.acquisition.MockSource;
import edu.csula.datascience.acquisition.S;
import edu.csula.datascience.acquisition.Source;

public class DataCollectorTest {
	private Collector<S, M> collector;
    private Source<M> source;

    @Before
    public void setup() {
        collector = new MockCollector();
        source = new MockSource();
    }
    
    public void getData(){
    	
    	List<CrimeModel> crime = new ArrayList<CrimeModel>();

    	Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("Crimedata");

		DBCollection collection = db.getCollection("2015");

    	//BasicDBObject query = new BasicDBObject();  
    	//query.put("empId", 10008);  
    	DBCursor cursor = (DBCursor) collection.findOne();  
    	while(cursor.hasNext()) {  
    	    System.out.println(cursor.next());  
    	    
    	    DBObject theObj = cursor.next();
    	    CrimeModel emp = (new Gson()).fromJson(theObj.toString(), CrimeModel.class);
            
    	    
    	    System.out.println(emp.toString());
    	    //How to get the DBObject value to ArrayList of Java Object?
//
//    	    BasicDBList studentsList = (BasicDBList) ((BSONObject) collection).get("Crimedata");
//    	    for (int i = 0; i < studentsList.size(); i++) {
//    	        BasicDBObject studentObj = (BasicDBObject) studentsList.get(i);
//    	        String case_number = studentObj.getString("case_number");
//    	        String district = studentObj.getString("district");
//    	        String date = studentObj.getString("date");
//    	        String description = studentObj.getString("desscription");
//
//    	        CrimeModel c = new CrimeModel();
//    	        c.setCase_number(case_number);
//    	        c.setDistrict(district);
//    	        c.setDate(date);
//    	        c.setDescription(description);
//
//    	        crime.add(c);
//    	        
//    	        for (CrimeModel cm : crime){
//    	        	System.out.println(crime);
//    	        }
//    	    }               
    	}
    }

    @Test
    public void mungee() throws Exception {
        List<S> list = (List<S>) collector.mungee(source.next());
        List<S> expectedList = Lists.newArrayList(
            new S("2", "content2"),
            new S("3", "content3")
        );

        Assert.assertEquals(list.size(), 2);

        for (int i = 0; i < 2; i ++) {
            Assert.assertEquals(list.get(i).getId(), expectedList.get(i).getId());
            Assert.assertEquals(list.get(i).getContent(), expectedList.get(i).getContent());
        }
    }
}