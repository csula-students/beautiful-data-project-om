package edu.csula.datascience.acquisition;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import edu.csula.datascience.acquisitionImpl.DataCollectorImpl;

public class CrimeDataTest {

	@Test
	public void mungee() throws Exception {

		DataCollectorImpl data = new DataCollectorImpl();
		
		//Perform mungee on data
		List<Crime> CrimeDataList = data.mungee();
		
		
		//Result after mungee
		Assert.assertEquals(CrimeDataList.size(), 47);

	}
}
