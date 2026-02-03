package com.example.firstProject.ecommerce.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnitUtil;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/dashboard/models")
public class ModelViewController {
	private static final List<ModelDescriptor> MODELS = new ArrayList<>();
	private static final Map<String, ModelDescriptor> MODEL_LOOKUP = new LinkedHashMap<>();
	private static final Set<Class<?>> SIMPLE_TYPES = Set.of(
			String.class,
			Integer.class,
			Long.class,
			Double.class,
			Float.class,
			Short.class,
			Byte.class,
			Boolean.class,
			BigDecimal.class,
			BigInteger.class,
			LocalDate.class,
			LocalDateTime.class,
			LocalTime.class,
			UUID.class
	);

	static {
		register("app-users", "App Users", com.example.firstProject.ecommerce.model.AppUser.class);
		register("attributes", "Attributes", com.example.firstProject.ecommerce.model.Attribute.class);
		register("attribute-values", "Attribute Values", com.example.firstProject.ecommerce.model.AttributeValue.class);
		register("cards", "Cards", com.example.firstProject.ecommerce.model.Card.class);
		register("card-items", "Card Items", com.example.firstProject.ecommerce.model.CardItem.class);
		register("categories", "Categories", com.example.firstProject.ecommerce.model.Category.class);
		register("coupons", "Coupons", com.example.firstProject.ecommerce.model.Coupon.class);
		register("customers", "Customers", com.example.firstProject.ecommerce.model.Customer.class);
		register("customer-addresses", "Customer Addresses", com.example.firstProject.ecommerce.model.CustomerAddress.class);
		register("galleries", "Galleries", com.example.firstProject.ecommerce.model.Gallery.class);
		register("notifications", "Notifications", com.example.firstProject.ecommerce.model.Notification.class);
		register("orders", "Orders", com.example.firstProject.ecommerce.model.Order.class);
		register("order-items", "Order Items", com.example.firstProject.ecommerce.model.OrderItem.class);
		register("order-statuses", "Order Statuses", com.example.firstProject.ecommerce.model.OrderStatusEntity.class);
		register("payments", "Payments", com.example.firstProject.ecommerce.model.Payment.class);
		register("products", "Products", com.example.firstProject.ecommerce.model.Product.class);
		register("product-attributes", "Product Attributes", com.example.firstProject.ecommerce.model.ProductAttribute.class);
		register("product-categories", "Product Categories", com.example.firstProject.ecommerce.model.ProductCategory.class);
		register("product-coupons", "Product Coupons", com.example.firstProject.ecommerce.model.ProductCoupon.class);
		register("product-shippings", "Product Shippings", com.example.firstProject.ecommerce.model.ProductShipping.class);
		register("product-tags", "Product Tags", com.example.firstProject.ecommerce.model.ProductTag.class);
		register("roles", "Roles", com.example.firstProject.ecommerce.model.Role.class);
		register("sells", "Sells", com.example.firstProject.ecommerce.model.Sell.class);
		register("shippers", "Shippers", com.example.firstProject.ecommerce.model.Shipper.class);
		register("shipping", "Shipping", com.example.firstProject.ecommerce.model.Shipping.class);
		register("slideshows", "Slideshows", com.example.firstProject.ecommerce.model.Slideshow.class);
		register("staff-accounts", "Staff Accounts", com.example.firstProject.ecommerce.model.StaffAccount.class);
		register("staff-roles", "Staff Roles", com.example.firstProject.ecommerce.model.StaffRole.class);
		register("suppliers", "Suppliers", com.example.firstProject.ecommerce.model.Supplier.class);
		register("tags", "Tags", com.example.firstProject.ecommerce.model.Tag.class);
		register("variants", "Variants", com.example.firstProject.ecommerce.model.Variant.class);
		register("variant-attribute-values", "Variant Attribute Values",
				com.example.firstProject.ecommerce.model.VariantAttributeValue.class);
		register("variant-values", "Variant Values", com.example.firstProject.ecommerce.model.VariantValue.class);
	}

	private final EntityManager entityManager;

	public ModelViewController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@GetMapping
	public String models(Model model) {
		model.addAttribute("models", MODELS);
		return "models";
	}

	@GetMapping("/{name}")
	public String modelDetails(@PathVariable String name, Model model) {
		ModelDescriptor descriptor = MODEL_LOOKUP.get(name);
		if (descriptor == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found");
		}

		List<?> entities = entityManager
				.createQuery("from " + descriptor.entityClass().getSimpleName(), descriptor.entityClass())
				.getResultList();

		List<String> headers = resolveHeaders(descriptor.entityClass());
		List<Map<String, Object>> rows = buildRows(entities, headers);

		model.addAttribute("modelLabel", descriptor.label());
		model.addAttribute("headers", headers);
		model.addAttribute("rows", rows);
		return "model-details";
	}

	private static void register(String slug, String label, Class<?> entityClass) {
		ModelDescriptor descriptor = new ModelDescriptor(slug, label, entityClass);
		MODELS.add(descriptor);
		MODEL_LOOKUP.put(slug, descriptor);
	}

	private List<String> resolveHeaders(Class<?> entityClass) {
		try {
			List<String> headers = new ArrayList<>();
			for (PropertyDescriptor descriptor : Introspector.getBeanInfo(entityClass, Object.class).getPropertyDescriptors()) {
				if (descriptor.getReadMethod() == null) {
					continue;
				}
				headers.add(descriptor.getName());
			}
			headers.sort(Comparator.naturalOrder());
			return headers;
		} catch (IntrospectionException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to inspect model", ex);
		}
	}

	private List<Map<String, Object>> buildRows(List<?> entities, List<String> headers) {
		List<Map<String, Object>> rows = new ArrayList<>();
		PersistenceUnitUtil util = entityManager.getEntityManagerFactory().getPersistenceUnitUtil();

		for (Object entity : entities) {
			Map<String, Object> row = new LinkedHashMap<>();
			for (String header : headers) {
				Object value = readProperty(entity, header);
				row.put(header, formatValue(value, util));
			}
			rows.add(row);
		}
		return rows;
	}

	private Object readProperty(Object entity, String propertyName) {
		try {
			for (PropertyDescriptor descriptor : Introspector.getBeanInfo(entity.getClass(), Object.class).getPropertyDescriptors()) {
				if (!Objects.equals(descriptor.getName(), propertyName)) {
					continue;
				}
				if (descriptor.getReadMethod() == null) {
					return null;
				}
				return descriptor.getReadMethod().invoke(entity);
			}
			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	private Object formatValue(Object value, PersistenceUnitUtil util) {
		if (value == null) {
			return "-";
		}
		Class<?> valueType = value.getClass();
		if (SIMPLE_TYPES.contains(valueType) || valueType.isEnum()) {
			return value;
		}
		if (value instanceof Collection<?> collection) {
			return "Collection (" + collection.size() + ")";
		}
		try {
			Object identifier = util.getIdentifier(value);
			if (identifier != null) {
				return identifier;
			}
		} catch (IllegalArgumentException ignored) {
		}
		return value.toString();
	}

	private record ModelDescriptor(String slug, String label, Class<?> entityClass) {
	}
}
