package edu.csula.datascience.acquisitionImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.csula.datascience.acquisition.Crime;
import edu.csula.datascience.acquisition.MockData;
import edu.csula.datascience.acquisition.SimpleModel;

public class DataCollectorImpl /*implements Collector<SimpleModel, MockData>*/ {

	
	public List<Crime> mungee() {

		GetDataForTestCase getData = new GetDataForTestCase();

		List<Crime> crimeDataList = getData
				.readFile("src/main/resources/testdata.json");

		List<Crime> crimeDataListTobeREmoved = new ArrayList<Crime>();
		List<String> casenumberNullID = new ArrayList<String>();

		for (Crime crime : crimeDataList) {

			if (crime.getCase_number().length() == 0
					|| crime.getCase_number() == null) {

				crimeDataListTobeREmoved.add(crime);

			}
		}
		crimeDataList.removeAll(crimeDataListTobeREmoved);

		// crimeDataList.re
		System.out.println(crimeDataList.size());

		return crimeDataList;

	}

	//@Override
	public void save(Collection<SimpleModel> data) {
		// TODO Auto-generated method stub

		int year = 2001;

		String jsonString = ReadJsonTest
				.callURL(
						"https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=75000&year=2015",
						000);

	}

	public static void main(String[] args) {

		/*
		 * ReadJsonTest r =new ReadJsonTest(); //r.getData(); List<CrimeModel>
		 * crimeModelsList=new ArrayList<CrimeModel>();
		 * crimeModelsList=r.getDataFromAPI(
		 * "https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=10&year=2015"
		 * ); r.StoreData(crimeModelsList);
		 */

		DataCollectorImpl d = new DataCollectorImpl();
		d.mungee();
	}

	//@Override
	public Collection<SimpleModel> mungee(Collection<MockData> src) {
		// TODO Auto-generated method stub
		return null;
	}

}
