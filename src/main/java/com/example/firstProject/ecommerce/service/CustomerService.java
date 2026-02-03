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
		customer.setLastName(request.lastName());
		customer.setCustomerClass(request.customerClass());
		customer.setRoom(request.room());
		customer.setBuilding(request.building());
		customer.setAddress1(request.address1());
		customer.setAddress2(request.address2());
		customer.setCity(request.city());
		customer.setState(request.state());
		customer.setPostalCode(request.postalCode());
		customer.setCountry(request.country());
		customer.setPhone(request.phone());
		customer.setVoiceMail(request.voiceMail());
		customer.setPassword(request.password());
		customer.setCreditCard(request.creditCard());
		customer.setCreditCardTypeId(request.creditCardTypeId());
		customer.setCardExpMo(request.cardExpMo());
		customer.setCardExpYr(request.cardExpYr());
		customer.setBillingAddress(request.billingAddress());
		customer.setBillingCity(request.billingCity());
		customer.setBillingRegion(request.billingRegion());
		customer.setBillingPostalCode(request.billingPostalCode());
		customer.setBillingCountry(request.billingCountry());
		customer.setShipAddress(request.shipAddress());
		customer.setShipCity(request.shipCity());
		customer.setShipRegion(request.shipRegion());
		customer.setShipPostalCode(request.shipPostalCode());
		customer.setShipCountry(request.shipCountry());
		return customerRepository.save(customer);
	}
}
