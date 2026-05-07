package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest(
		@NotBlank String name,
		@Email @NotBlank String email,
		String lastName,
		String customerClass,
		String room,
		String building,
		String address1,
		String address2,
		String city,
		String state,
		String postalCode,
		String country,
		String phone,
		String voiceMail,
		String password,
		String creditCard,
		String creditCardTypeId,
		String cardExpMo,
		String cardExpYr,
		String billingAddress,
		String billingCity,
		String billingRegion,
		String billingPostalCode,
		String billingCountry,
		String shipAddress,
		String shipCity,
		String shipRegion,
		String shipPostalCode,
		String shipCountry,
		String address
) {
}

