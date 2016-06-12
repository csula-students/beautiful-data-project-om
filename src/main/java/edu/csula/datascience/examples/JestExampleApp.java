package edu.csula.datascience.examples;

import com.google.common.collect.Lists;
import io.searchbox.action.BulkableAction;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * A quick example app to send data to elastic search on AWS
 */
public class JestExampleApp {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String indexName = "crimeaws";
        String typeName = "cityaws";
        String awsAddress = "https://search-bigdata-zh3liaak7tmwx7ylbloqrrsbcy.us-west-2.es.amazonaws.com";
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
            .Builder(awsAddress)
            .multiThreaded(true)
            .build());
        JestClient client = factory.getObject();

        // as usual process to connect to data source, we will need to set up
        // node and client// to read CSV file from the resource folder
        File csv = new File(
            ClassLoader.getSystemResource("CrimeData.csv")
                .toURI()
        );

        try {
            // after reading the csv file, we will use CSVParser to parse through
            // the csv files
            CSVParser parser = CSVParser.parse(
                csv,
                Charset.defaultCharset(),
                CSVFormat.EXCEL.withHeader()
            );
            Collection<Crime> temperatures = Lists.newArrayList();

            int count = 0;

            // for each record, we will insert data into Elastic Search
//            parser.forEach(record -> {
            for (CSVRecord record: parser) {
                // cleaning up dirty data which doesn't have time or temperature
            	 if (
                         !record.get("id").isEmpty() &&
                         !record.get("case_number").isEmpty() 
                   /*      !record.get("date").isEmpty() &&
                         !record.get("block").isEmpty()&&
                         !record.get("iucr").isEmpty()&&
                         !record.get("primary_type").isEmpty()&&    
                         !record.get("description").isEmpty()&&
                         !record.get("location_description").isEmpty()&&
                         !record.get("beat").isEmpty()&&
                         !record.get("district").isEmpty()&&
                         !record.get("ward").isEmpty()&&
                         !record.get("community_area").isEmpty()&&
                         !record.get("fbi_code").isEmpty()&&
                         !record.get("x_coordinate").isEmpty()&&
                         !record.get("y_coordinate").isEmpty()&&
                         !record.get("year").isEmpty()&&
                         !record.get("updated_on").isEmpty()&&
                         !record.get("latitude").isEmpty()&&
                         !record.get("longitude").isEmpty()&&
                         !record.get("location").isEmpty()*/
                     ) 
                
                
                {
                	Crime temp = new Crime(
                            record.get("id"),

                            record.get("case_number"),
                            record.get("date"),
                            record.get("block"),
                            record.get("iucr"),
                            record.get("primary_type"),    
                            record.get("description"),
                            record.get("location_description"),
                            Boolean.parseBoolean(record.get("arrest")),
                            Boolean.parseBoolean(record.get("domestic")),
                            record.get("beat"),
                            record.get("district"),
                            record.get("ward"),
                            record.get("community_area"),
                            record.get("fbi_code"), 
                            record.get("x_coordinate"),
                            record.get("y_coordinate"),
                            record.get("year"),
                            record.get("updated_on"),
                            record.get("latitude"),
                            record.get("longitude"),
                            record.get("location")
                        );

                    if (count < 500) {
                        temperatures.add(temp);
                        count ++;
                    } else {
                        try {
                            Collection<BulkableAction> actions = Lists.newArrayList();
                            temperatures.stream()
                                .forEach(tmp -> {
                                    actions.add(new Index.Builder(tmp).build());
                                });
                            Bulk.Builder bulk = new Bulk.Builder()
                                .defaultIndex(indexName)
                                .defaultType(typeName)
                                .addAction(actions);
                            client.execute(bulk.build());
                            count = 0;
                            temperatures = Lists.newArrayList();
                            System.out.println("Inserted 500 documents to cloud");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            Collection<BulkableAction> actions = Lists.newArrayList();
            temperatures.stream()
                .forEach(tmp -> {
                    actions.add(new Index.Builder(tmp).build());
                });
            Bulk.Builder bulk = new Bulk.Builder()
                .defaultIndex(indexName)
                .defaultType(typeName)
                .addAction(actions);
            client.execute(bulk.build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("We are done! Yay!");
    }

    static class Crime {
    	final  String id;
    	final  String case_number;
    	final String date;
    	final String block;
    	final  String iucr;
        final  String primary_type;
    	final  String description;
        final  String location_description;
    	final  boolean arrest;
    	final  boolean domestic;
    	final  String beat;
    	final  String district;
        final  String ward;
    	final  String community_area;   
    	final  String fbi_code;
        final  String x_coordinate;
        final  String y_coordinate;
        final  String year;
        final  String updated_on;
    	final  String latitude;
        final  String longitude;
        final  String location;
		public Crime(String id, String case_number, String date, String block,
				String iucr, String primary_type, String description,
				String location_description, boolean arrest, boolean domestic,
				String beat, String district, String ward,
				String community_area, String fbi_code, String x_coordinate,
				String y_coordinate, String year, String updated_on,
				String latitude, String longitude, String location) {
			super();
			this.id = id;
			this.case_number = case_number;
			this.date = date;
			this.block = block;
			this.iucr = iucr;
			this.primary_type = primary_type;
			this.description = description;
			this.location_description = location_description;
			this.arrest = arrest;
			this.domestic = domestic;
			this.beat = beat;
			this.district = district;
			this.ward = ward;
			this.community_area = community_area;
			this.fbi_code = fbi_code;
			this.x_coordinate = x_coordinate;
			this.y_coordinate = y_coordinate;
			this.year = year;
			this.updated_on = updated_on;
			this.latitude = latitude;
			this.longitude = longitude;
			this.location = location;
		}

    }

}
