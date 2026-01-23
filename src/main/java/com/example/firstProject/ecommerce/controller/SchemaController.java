package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.model.Attribute;
import com.example.firstProject.ecommerce.model.AttributeValue;
import com.example.firstProject.ecommerce.model.Card;
import com.example.firstProject.ecommerce.model.CardItem;
import com.example.firstProject.ecommerce.model.Coupon;
import com.example.firstProject.ecommerce.model.CustomerAddress;
import com.example.firstProject.ecommerce.model.Gallery;
import com.example.firstProject.ecommerce.model.Notification;
import com.example.firstProject.ecommerce.model.OrderStatusEntity;
import com.example.firstProject.ecommerce.model.ProductAttribute;
import com.example.firstProject.ecommerce.model.ProductCategory;
import com.example.firstProject.ecommerce.model.ProductCoupon;
import com.example.firstProject.ecommerce.model.ProductShipping;
import com.example.firstProject.ecommerce.model.ProductTag;
import com.example.firstProject.ecommerce.model.Role;
import com.example.firstProject.ecommerce.model.Sell;
import com.example.firstProject.ecommerce.model.Shipping;
import com.example.firstProject.ecommerce.model.Slideshow;
import com.example.firstProject.ecommerce.model.StaffAccount;
import com.example.firstProject.ecommerce.model.StaffRole;
import com.example.firstProject.ecommerce.model.Tag;
import com.example.firstProject.ecommerce.model.Variant;
import com.example.firstProject.ecommerce.model.VariantAttributeValue;
import com.example.firstProject.ecommerce.model.VariantValue;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/schema")
public class SchemaController {
	private final EntityManager entityManager;

	public SchemaController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@GetMapping("/tags")
	public List<Tag> listTags() {
		return entityManager.createQuery("from Tag", Tag.class).getResultList();
	}

	@GetMapping("/tags/{id}")
	public Tag getTag(@PathVariable Integer id) {
		return findOrThrow(Tag.class, id, "Tag");
	}

	@GetMapping("/attributes")
	public List<Attribute> listAttributes() {
		return entityManager.createQuery("from Attribute", Attribute.class).getResultList();
	}

	@GetMapping("/attributes/{id}")
	public Attribute getAttribute(@PathVariable UUID id) {
		return findOrThrow(Attribute.class, id, "Attribute");
	}

	@GetMapping("/attribute-values")
	public List<AttributeValue> listAttributeValues() {
		return entityManager.createQuery("from AttributeValue", AttributeValue.class).getResultList();
	}

	@GetMapping("/attribute-values/{id}")
	public AttributeValue getAttributeValue(@PathVariable UUID id) {
		return findOrThrow(AttributeValue.class, id, "AttributeValue");
	}

	@GetMapping("/variants")
	public List<Variant> listVariants() {
		return entityManager.createQuery("from Variant", Variant.class).getResultList();
	}

	@GetMapping("/variants/{id}")
	public Variant getVariant(@PathVariable UUID id) {
		return findOrThrow(Variant.class, id, "Variant");
	}

	@GetMapping("/variant-values")
	public List<VariantValue> listVariantValues() {
		return entityManager.createQuery("from VariantValue", VariantValue.class).getResultList();
	}

	@GetMapping("/variant-values/{id}")
	public VariantValue getVariantValue(@PathVariable UUID id) {
		return findOrThrow(VariantValue.class, id, "VariantValue");
	}

	@GetMapping("/variant-attribute-values")
	public List<VariantAttributeValue> listVariantAttributeValues() {
		return entityManager.createQuery("from VariantAttributeValue", VariantAttributeValue.class).getResultList();
	}

	@GetMapping("/variant-attribute-values/{id}")
	public VariantAttributeValue getVariantAttributeValue(@PathVariable UUID id) {
		return findOrThrow(VariantAttributeValue.class, id, "VariantAttributeValue");
	}

	@GetMapping("/coupons")
	public List<Coupon> listCoupons() {
		return entityManager.createQuery("from Coupon", Coupon.class).getResultList();
	}

	@GetMapping("/coupons/{id}")
	public Coupon getCoupon(@PathVariable Integer id) {
		return findOrThrow(Coupon.class, id, "Coupon");
	}

	@GetMapping("/product-tags")
	public List<ProductTag> listProductTags() {
		return entityManager.createQuery("from ProductTag", ProductTag.class).getResultList();
	}

	@GetMapping("/product-categories")
	public List<ProductCategory> listProductCategories() {
		return entityManager.createQuery("from ProductCategory", ProductCategory.class).getResultList();
	}

	@GetMapping("/product-attributes")
	public List<ProductAttribute> listProductAttributes() {
		return entityManager.createQuery("from ProductAttribute", ProductAttribute.class).getResultList();
	}

	@GetMapping("/product-coupons")
	public List<ProductCoupon> listProductCoupons() {
		return entityManager.createQuery("from ProductCoupon", ProductCoupon.class).getResultList();
	}

	@GetMapping("/galleries")
	public List<Gallery> listGalleries() {
		return entityManager.createQuery("from Gallery", Gallery.class).getResultList();
	}

	@GetMapping("/galleries/{id}")
	public Gallery getGallery(@PathVariable UUID id) {
		return findOrThrow(Gallery.class, id, "Gallery");
	}

	@GetMapping("/sells")
	public List<Sell> listSells() {
		return entityManager.createQuery("from Sell", Sell.class).getResultList();
	}

	@GetMapping("/sells/{id}")
	public Sell getSell(@PathVariable UUID id) {
		return findOrThrow(Sell.class, id, "Sell");
	}

	@GetMapping("/shippings")
	public List<Shipping> listShippings() {
		return entityManager.createQuery("from Shipping", Shipping.class).getResultList();
	}

	@GetMapping("/shippings/{id}")
	public Shipping getShipping(@PathVariable Integer id) {
		return findOrThrow(Shipping.class, id, "Shipping");
	}

	@GetMapping("/product-shippings")
	public List<ProductShipping> listProductShippings() {
		return entityManager.createQuery("from ProductShipping", ProductShipping.class).getResultList();
	}

	@GetMapping("/order-statuses")
	public List<OrderStatusEntity> listOrderStatuses() {
		return entityManager.createQuery("from OrderStatusEntity", OrderStatusEntity.class).getResultList();
	}

	@GetMapping("/order-statuses/{id}")
	public OrderStatusEntity getOrderStatus(@PathVariable Integer id) {
		return findOrThrow(OrderStatusEntity.class, id, "OrderStatus");
	}

	@GetMapping("/cards")
	public List<Card> listCards() {
		return entityManager.createQuery("from Card", Card.class).getResultList();
	}

	@GetMapping("/cards/{id}")
	public Card getCard(@PathVariable UUID id) {
		return findOrThrow(Card.class, id, "Card");
	}

	@GetMapping("/card-items")
	public List<CardItem> listCardItems() {
		return entityManager.createQuery("from CardItem", CardItem.class).getResultList();
	}

	@GetMapping("/card-items/{id}")
	public CardItem getCardItem(@PathVariable UUID id) {
		return findOrThrow(CardItem.class, id, "CardItem");
	}

	@GetMapping("/customer-addresses")
	public List<CustomerAddress> listCustomerAddresses() {
		return entityManager.createQuery("from CustomerAddress", CustomerAddress.class).getResultList();
	}

	@GetMapping("/customer-addresses/{id}")
	public CustomerAddress getCustomerAddress(@PathVariable UUID id) {
		return findOrThrow(CustomerAddress.class, id, "CustomerAddress");
	}

	@GetMapping("/roles")
	public List<Role> listRoles() {
		return entityManager.createQuery("from Role", Role.class).getResultList();
	}

	@GetMapping("/roles/{id}")
	public Role getRole(@PathVariable Integer id) {
		return findOrThrow(Role.class, id, "Role");
	}

	@GetMapping("/staff-accounts")
	public List<StaffAccount> listStaffAccounts() {
		return entityManager.createQuery("from StaffAccount", StaffAccount.class).getResultList();
	}

	@GetMapping("/staff-accounts/{id}")
	public StaffAccount getStaffAccount(@PathVariable UUID id) {
		return findOrThrow(StaffAccount.class, id, "StaffAccount");
	}

	@GetMapping("/staff-roles")
	public List<StaffRole> listStaffRoles() {
		return entityManager.createQuery("from StaffRole", StaffRole.class).getResultList();
	}

	@GetMapping("/notifications")
	public List<Notification> listNotifications() {
		return entityManager.createQuery("from Notification", Notification.class).getResultList();
	}

	@GetMapping("/notifications/{id}")
	public Notification getNotification(@PathVariable UUID id) {
		return findOrThrow(Notification.class, id, "Notification");
	}

	@GetMapping("/slideshows")
	public List<Slideshow> listSlideshows() {
		return entityManager.createQuery("from Slideshow", Slideshow.class).getResultList();
	}

	@GetMapping("/slideshows/{id}")
	public Slideshow getSlideshow(@PathVariable UUID id) {
		return findOrThrow(Slideshow.class, id, "Slideshow");
	}

	private <T, K> T findOrThrow(Class<T> type, K id, String label) {
		T entity = entityManager.find(type, id);
		if (entity == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, label + " not found");
		}
		return entity;
	}
}
