//package edu.csula.datascience.acquisition;




package edu.csula.datascience.acquisition;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CollectorImpl {

	public static void main(String[] args) {

		CollectorImpl obj = new CollectorImpl();

		String domainName = "google.com";
		
		//in mac oxs
		//String command = "ping -c 3 " + domainName;
		
		//in windows
		//String command = "ping -n 3 " + domainName;
		//System.setProperty("user.dir", "C:\\Program Files\\Flowella");
		
		//String command ="mongoimport --db crimes --collection  crimedatas --drop --file C:/Users/Akhil/Desktop/datasets/rows.json";
		
		for(int i=1;i<=31;i++){
		String command ="mongoimport -d crime -c crimedata --type csv --file D:/CSULA/CA594_data/crime/split/split/CrimeData2001to2015_chunk"+i+".csv --headerline";
		String output = obj.executeCommand(command);
		}
		//System.out.println(output);
		System.out.println("finished");

	} 

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}