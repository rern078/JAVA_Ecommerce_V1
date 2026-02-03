package com.example.firstProject.ecommerce.dto;

import java.time.LocalDateTime;

public record CustomerResponse(
		Long id,
		String name,
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
		String email,
		String voiceMail,
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
		String address,
		LocalDateTime createdAt
) {
}
