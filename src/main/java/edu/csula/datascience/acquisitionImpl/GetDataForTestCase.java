package edu.csula.datascience.acquisitionImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import edu.csula.datascience.acquisition.Crime;
import edu.csula.datascience.acquisition.CrimeModel;

public class GetDataForTestCase {

	public List<Crime> getDataFromFile(String path) {

		List<Crime> crimedataList = new ArrayList<Crime>();
		path = "D:/testdata.json";

		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(path);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String data = null;

			while ((line = bufferedReader.readLine()) != null) {

				// data=line;
				// System.out.println(line);
			}

			ObjectMapper mapper = new ObjectMapper();

			List<DBObject> b = new ArrayList<DBObject>();

			b = (List<DBObject>) JSON.parse(data);
			System.out.println("size--->" + b.size());

			for (DBObject doc : b) {

				Crime obj = mapper.readValue(doc.toString(), Crime.class);

				crimedataList.add(obj);
			}

			System.out.println(crimedataList.size());

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + path + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + path + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return null;
	}

	public static List<Crime> readFile(String filename) {
		String result = "";
		List<Crime> crimedataList = new ArrayList<Crime>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();

			ObjectMapper mapper = new ObjectMapper();

			List<DBObject> b = new ArrayList<DBObject>();

			b = (List<DBObject>) JSON.parse(result);

			for (DBObject doc : b) {

				Crime obj = mapper.readValue(doc.toString(), Crime.class);

				crimedataList.add(obj);
			}

			// System.out.println(crimedataList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return crimedataList;
	}

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {

		List<Crime> crimedataList = readFile("D:/testdata.json");

		System.out.println(crimedataList.size());
	}

}
