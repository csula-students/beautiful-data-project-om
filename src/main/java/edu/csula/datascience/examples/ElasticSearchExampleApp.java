package edu.csula.datascience.examples;

import com.google.gson.Gson;

//import edu.csula.datascience.acquisition.CrimeModel.Location;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * A quick elastic search example app
 *
 * It will parse the csv file from the resource folder under main and send these
 * data to elastic search instance running locally
 *
 * After that we will be using elastic search to do full text search
 *
 * gradle command to run this app `gradle esExample`
 */

// Pratik and ashish
public class ElasticSearchExampleApp {
    private final static String indexName = "crime1";
    private final static String typeName = "city1";

    public static void main(String[] args) throws URISyntaxException, IOException {
        Node node = nodeBuilder().settings(Settings.builder()
            .put("cluster.name", "crime")
            .put("path.home", "elasticsearch-data")).node();
        Client client = node.client();

        /**
         * 
         *
         *
         * INSERT data to elastic search
         */

        // as usual process to connect to data source, we will need to set up
        // node and client// to read CSV file from the resource folder
        File csv = new File(
            ClassLoader.getSystemResource("CrimeData.csv")
                .toURI()
        );

        // create bulk processor
        BulkProcessor bulkProcessor = BulkProcessor.builder(
            client,
            new BulkProcessor.Listener() {
                @Override
                public void beforeBulk(long executionId,
                                       BulkRequest request) {
                }

                @Override
                public void afterBulk(long executionId,
                                      BulkRequest request,
                                      BulkResponse response) {
                }

                @Override
                public void afterBulk(long executionId,
                                      BulkRequest request,
                                      Throwable failure) {
                    System.out.println("Facing error while importing data to elastic search");
                    failure.printStackTrace();
                }
            })
            .setBulkActions(10000)
            .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
            .setFlushInterval(TimeValue.timeValueSeconds(5))
            .setConcurrentRequests(1)
            .setBackoffPolicy(
                BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
            .build();

        // Gson library for sending json to elastic search
        Gson gson = new Gson();

        try {
            // after reading the csv file, we will use CSVParser to parse through
            // the csv files
            CSVParser parser = CSVParser.parse(
                csv,
                Charset.defaultCharset(),
                CSVFormat.EXCEL.withHeader()
            );

            // for each record, we will insert data into Elastic Search
            parser.forEach(record -> {
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

                    bulkProcessor.add(new IndexRequest(indexName, typeName)
                        .source(gson.toJson(temp))
                    );
                    
                }
               
            });
            
            System.out.println("finished");

        } catch (IOException e) {
            e.printStackTrace();
        }
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