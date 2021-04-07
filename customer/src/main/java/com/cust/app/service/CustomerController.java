package com.cust.app.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/InviteCustomers")
	public ResponseEntity<List<Customer>> getCustomerRecords() throws IOException{
		
		List<Customer> customers= customerService.readCustomerData();
		
		Location dublinLocation=new Location(Constants.DUBLIN_OFFICE_LATITUDE, Constants.DUBLIN_OFFICE_LONGITUDE);
		
		for (Customer customer : customers) {
			
			Location custLocation=new Location(Double.parseDouble(customer.getLatitude()), Double.parseDouble(customer.getLongitude()));
			
			Double distance=customerService.calculateDistance(custLocation,dublinLocation);
			
			if(distance>=100D) {
				
				customers.remove(customer);
				
			}
			
		}
		
		Collections.sort(customers);
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.ACCEPTED);
		
	}
	
	
}
