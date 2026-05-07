package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.dto.CreateCustomerRequest;
import com.example.firstProject.ecommerce.dto.CustomerResponse;
import com.example.firstProject.ecommerce.dto.UpdateCustomerRequest;
import com.example.firstProject.ecommerce.model.Customer;
import com.example.firstProject.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<CustomerResponse> listCustomers() {
		return customerService.listCustomers().stream()
				.map(CustomerController::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public CustomerResponse getCustomer(@PathVariable Long id) {
		return toResponse(customerService.getCustomer(id));
	}

	@PostMapping
	public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
		Customer created = customerService.createCustomer(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
	}

	@PutMapping("/{id}")
	public CustomerResponse updateCustomer(@PathVariable Long id, @Valid @RequestBody UpdateCustomerRequest request) {
		return toResponse(customerService.updateCustomer(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}

	private static CustomerResponse toResponse(Customer customer) {
		return new CustomerResponse(
				customer.getId(),
				customer.getName(),
				customer.getLastName(),
				customer.getCustomerClass(),
				customer.getRoom(),
				customer.getBuilding(),
				customer.getAddress1(),
				customer.getAddress2(),
				customer.getCity(),
				customer.getState(),
				customer.getPostalCode(),
				customer.getCountry(),
				customer.getPhone(),
				customer.getEmail(),
				customer.getVoiceMail(),
				customer.getCreditCard(),
				customer.getCreditCardTypeId(),
				customer.getCardExpMo(),
				customer.getCardExpYr(),
				customer.getBillingAddress(),
				customer.getBillingCity(),
				customer.getBillingRegion(),
				customer.getBillingPostalCode(),
				customer.getBillingCountry(),
				customer.getShipAddress(),
				customer.getShipCity(),
				customer.getShipRegion(),
				customer.getShipPostalCode(),
				customer.getShipCountry(),
				customer.getAddress(),
				customer.getCreatedAt()
		);
	}
}
