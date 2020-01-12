package com.gencay10.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gencay10.springdemo.entity.Customer;
import com.gencay10.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getCustomer() {

		return customerService.getCustomers();

	}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);

		if (theCustomer == null) {
			throw new CustomerNotFoundException("Bu id de kullanýcý bulunamadý: " + customerId);
		}

		return theCustomer;
	}

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		theCustomer.setId(0);

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		Customer tempCustomer = customerService.getCustomer(customerId);

		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Bu id de kullanýcý bulunamadý: " + customerId);
		}

		customerService.deleteCustomer(customerId);

		return "Bu id deki kullanýcý silindi: " + customerId;
	}

}
