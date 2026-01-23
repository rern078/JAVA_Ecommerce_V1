package com.example.firstProject.ecommerce.service;

import com.example.firstProject.ecommerce.dto.CreateCustomerRequest;
import com.example.firstProject.ecommerce.exception.BadRequestException;
import com.example.firstProject.ecommerce.exception.NotFoundException;
import com.example.firstProject.ecommerce.model.Customer;
import com.example.firstProject.ecommerce.repository.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> listCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomer(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Customer not found: " + id));
	}

	public Customer createCustomer(CreateCustomerRequest request) {
		if (customerRepository.findByEmail(request.email()).isPresent()) {
			throw new BadRequestException("Customer email already exists: " + request.email());
		}
		Customer customer = new Customer(request.name(), request.email(), request.address());
		return customerRepository.save(customer);
	}
}
