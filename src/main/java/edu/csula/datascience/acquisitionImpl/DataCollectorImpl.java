package edu.csula.datascience.acquisitionImpl;

import java.util.Collection;

import edu.csula.datascience.acquisition.Collector;
import edu.csula.datascience.acquisition.MockData;
import edu.csula.datascience.acquisition.SimpleModel;

public class DataCollectorImpl implements Collector<SimpleModel, MockData>{


	@Override
	public Collection<SimpleModel> mungee(Collection<MockData> src) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Collection<SimpleModel> data) {
		// TODO Auto-generated method stub
		
		int year = 2001;

//		for (int i = year; i <= 2015; i++) {
//			String jsonString = ReadJsonTest.callURL(
//					"https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=100000&year="
//							+ i, i);
//
//			System.out.println("============== success for =========== " + i
//					+ "============================");
//		}
		
		String jsonString =
				ReadJsonTest.callURL("https://data.cityofchicago.org/resource/6zsd-86xi.json?$limit=75000&year=2015",000);

		
	}

}
