package com.cust.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class CustomerService {

	public List<Customer> readCustomerData() throws IOException {

		Resource resource = new ClassPathResource("customers.txt");

		File inputFile = resource.getFile();

		inputFile.createNewFile();

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));

		List<Customer> customers = new CopyOnWriteArrayList<Customer>();

		try {
			StringBuilder sb = new StringBuilder();
			
			String line = reader.readLine();

			Gson gson = new Gson();

			while (line != null) {

				sb.append(line);

				Customer customer = gson.fromJson(line, Customer.class);
				customers.add(customer);
				sb.append("\n");

				line = reader.readLine();

			}
		} finally {
			reader.close();
		}

		return customers;

	}
	
	public Double calculateDistance(Location source, Location target) {

		Double lon1 = Math.toRadians(source.getLongitude());
		Double lon2 = Math.toRadians(target.getLongitude());
		Double lat1 = Math.toRadians(source.getLatitude());
		Double lat2 = Math.toRadians(target.getLatitude());

		Double dlon = lon2 - lon1;
		Double dlat = lat2 - lat1;
		Double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		Double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956
		// for miles
		Double r = 6371D;

		// calculate the result
		return (c * r);
	}

}
