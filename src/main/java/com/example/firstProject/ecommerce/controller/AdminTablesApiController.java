package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.dto.RoleRequest;
import com.example.firstProject.ecommerce.dto.SellRequest;
import com.example.firstProject.ecommerce.dto.ShipperRequest;
import com.example.firstProject.ecommerce.dto.ShippingRequest;
import com.example.firstProject.ecommerce.dto.SlideshowRequest;
import com.example.firstProject.ecommerce.dto.StaffAccountRequest;
import com.example.firstProject.ecommerce.dto.StaffRoleRequest;
import com.example.firstProject.ecommerce.dto.SupplierRequest;
import com.example.firstProject.ecommerce.dto.TagRequest;
import com.example.firstProject.ecommerce.exception.BadRequestException;
import com.example.firstProject.ecommerce.exception.NotFoundException;
import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.model.Role;
import com.example.firstProject.ecommerce.model.Sell;
import com.example.firstProject.ecommerce.model.Shipper;
import com.example.firstProject.ecommerce.model.Shipping;
import com.example.firstProject.ecommerce.model.Slideshow;
import com.example.firstProject.ecommerce.model.StaffAccount;
import com.example.firstProject.ecommerce.model.StaffRole;
import com.example.firstProject.ecommerce.model.StaffRoleId;
import com.example.firstProject.ecommerce.model.Supplier;
import com.example.firstProject.ecommerce.model.Tag;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@Transactional
public class AdminTablesApiController {
	private final EntityManager entityManager;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public AdminTablesApiController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@GetMapping("/roles")
	public List<Role> listRoles() {
		return entityManager.createQuery("from Role", Role.class).getResultList();
	}

	@PostMapping("/roles")
	public ResponseEntity<Role> createRole(@Valid @RequestBody RoleRequest request) {
		Role role = new Role();
		role.setRoleName(request.roleName());
		role.setPrivileges(request.privileges());
		entityManager.persist(role);
		return ResponseEntity.status(HttpStatus.CREATED).body(role);
	}

	@PutMapping("/roles/{id}")
	public Role updateRole(@PathVariable Integer id, @Valid @RequestBody RoleRequest request) {
		Role role = findOrThrow(Role.class, id, "Role");
		role.setRoleName(request.roleName());
		role.setPrivileges(request.privileges());
		return role;
	}

	@DeleteMapping("/roles/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
		Role role = findOrThrow(Role.class, id, "Role");
		entityManager.remove(role);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/sells")
	public List<Sell> listSells() {
		return entityManager.createQuery("from Sell", Sell.class).getResultList();
	}

	@PostMapping("/sells")
	public ResponseEntity<Sell> createSell(@Valid @RequestBody SellRequest request) {
		Product product = findOrThrow(Product.class, request.productId(), "Product");
		Sell sell = new Sell();
		sell.setProduct(product);
		sell.setPrice(request.price());
		sell.setQuantity(request.quantity());
		entityManager.persist(sell);
		return ResponseEntity.status(HttpStatus.CREATED).body(sell);
	}

	@PutMapping("/sells/{id}")
	public Sell updateSell(@PathVariable UUID id, @Valid @RequestBody SellRequest request) {
		Sell sell = findOrThrow(Sell.class, id, "Sell");
		Product product = findOrThrow(Product.class, request.productId(), "Product");
		sell.setProduct(product);
		sell.setPrice(request.price());
		sell.setQuantity(request.quantity());
		return sell;
	}

	@DeleteMapping("/sells/{id}")
	public ResponseEntity<Void> deleteSell(@PathVariable UUID id) {
		Sell sell = findOrThrow(Sell.class, id, "Sell");
		entityManager.remove(sell);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/shippers")
	public List<Shipper> listShippers() {
		return entityManager.createQuery("from Shipper", Shipper.class).getResultList();
	}

	@PostMapping("/shippers")
	public ResponseEntity<Shipper> createShipper(@Valid @RequestBody ShipperRequest request) {
		Shipper shipper = new Shipper();
		shipper.setCompanyName(request.companyName());
		shipper.setPhone(request.phone());
		entityManager.persist(shipper);
		return ResponseEntity.status(HttpStatus.CREATED).body(shipper);
	}

	@PutMapping("/shippers/{id}")
	public Shipper updateShipper(@PathVariable Long id, @Valid @RequestBody ShipperRequest request) {
		Shipper shipper = findOrThrow(Shipper.class, id, "Shipper");
		shipper.setCompanyName(request.companyName());
		shipper.setPhone(request.phone());
		return shipper;
	}

	@DeleteMapping("/shippers/{id}")
	public ResponseEntity<Void> deleteShipper(@PathVariable Long id) {
		Shipper shipper = findOrThrow(Shipper.class, id, "Shipper");
		entityManager.remove(shipper);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/shippings")
	public List<Shipping> listShippings() {
		return entityManager.createQuery("from Shipping", Shipping.class).getResultList();
	}

	@PostMapping("/shippings")
	public ResponseEntity<Shipping> createShipping(@RequestBody ShippingRequest request) {
		Shipping shipping = new Shipping();
		shipping.setName(request.name());
		shipping.setActive(request.active());
		shipping.setIconPath(request.iconPath());
		entityManager.persist(shipping);
		return ResponseEntity.status(HttpStatus.CREATED).body(shipping);
	}

	@PutMapping("/shippings/{id}")
	public Shipping updateShipping(@PathVariable Integer id, @RequestBody ShippingRequest request) {
		Shipping shipping = findOrThrow(Shipping.class, id, "Shipping");
		shipping.setName(request.name());
		shipping.setActive(request.active());
		shipping.setIconPath(request.iconPath());
		return shipping;
	}

	@DeleteMapping("/shippings/{id}")
	public ResponseEntity<Void> deleteShipping(@PathVariable Integer id) {
		Shipping shipping = findOrThrow(Shipping.class, id, "Shipping");
		entityManager.remove(shipping);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/slideshows")
	public List<Slideshow> listSlideshows() {
		return entityManager.createQuery("from Slideshow", Slideshow.class).getResultList();
	}

	@PostMapping("/slideshows")
	public ResponseEntity<Slideshow> createSlideshow(@RequestBody SlideshowRequest request) {
		Slideshow slideshow = new Slideshow();
		slideshow.setDestinationUrl(request.destinationUrl());
		slideshow.setImageUrl(request.imageUrl());
		slideshow.setClicks(request.clicks());
		entityManager.persist(slideshow);
		return ResponseEntity.status(HttpStatus.CREATED).body(slideshow);
	}

	@PutMapping("/slideshows/{id}")
	public Slideshow updateSlideshow(@PathVariable UUID id, @RequestBody SlideshowRequest request) {
		Slideshow slideshow = findOrThrow(Slideshow.class, id, "Slideshow");
		slideshow.setDestinationUrl(request.destinationUrl());
		slideshow.setImageUrl(request.imageUrl());
		slideshow.setClicks(request.clicks());
		return slideshow;
	}

	@DeleteMapping("/slideshows/{id}")
	public ResponseEntity<Void> deleteSlideshow(@PathVariable UUID id) {
		Slideshow slideshow = findOrThrow(Slideshow.class, id, "Slideshow");
		entityManager.remove(slideshow);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/staff-accounts")
	public List<StaffAccount> listStaffAccounts() {
		return entityManager.createQuery("from StaffAccount", StaffAccount.class).getResultList();
	}

	@PostMapping("/staff-accounts")
	public ResponseEntity<StaffAccount> createStaffAccount(@RequestBody StaffAccountRequest request) {
		StaffAccount staff = new StaffAccount();
		applyStaffAccount(staff, request, true);
		entityManager.persist(staff);
		return ResponseEntity.status(HttpStatus.CREATED).body(staff);
	}

	@PutMapping("/staff-accounts/{id}")
	public StaffAccount updateStaffAccount(@PathVariable UUID id, @RequestBody StaffAccountRequest request) {
		StaffAccount staff = findOrThrow(StaffAccount.class, id, "StaffAccount");
		applyStaffAccount(staff, request, false);
		return staff;
	}

	@DeleteMapping("/staff-accounts/{id}")
	public ResponseEntity<Void> deleteStaffAccount(@PathVariable UUID id) {
		StaffAccount staff = findOrThrow(StaffAccount.class, id, "StaffAccount");
		entityManager.remove(staff);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/staff-roles")
	public List<StaffRole> listStaffRoles() {
		return entityManager.createQuery("from StaffRole", StaffRole.class).getResultList();
	}

	@PostMapping("/staff-roles")
	public ResponseEntity<StaffRole> createStaffRole(@Valid @RequestBody StaffRoleRequest request) {
		StaffAccount staff = findOrThrow(StaffAccount.class, request.staffId(), "StaffAccount");
		Role role = findOrThrow(Role.class, request.roleId(), "Role");
		StaffRole staffRole = new StaffRole();
		staffRole.setStaff(staff);
		staffRole.setRole(role);
		entityManager.persist(staffRole);
		return ResponseEntity.status(HttpStatus.CREATED).body(staffRole);
	}

	@PutMapping("/staff-roles/{staffId}/{roleId}")
	public StaffRole updateStaffRole(@PathVariable UUID staffId,
									 @PathVariable Integer roleId,
									 @Valid @RequestBody StaffRoleRequest request) {
		StaffRoleId id = new StaffRoleId(staffId, roleId);
		StaffRole existing = findOrThrow(StaffRole.class, id, "StaffRole");
		entityManager.remove(existing);
		entityManager.flush();
		StaffAccount staff = findOrThrow(StaffAccount.class, request.staffId(), "StaffAccount");
		Role role = findOrThrow(Role.class, request.roleId(), "Role");
		StaffRole staffRole = new StaffRole();
		staffRole.setStaff(staff);
		staffRole.setRole(role);
		entityManager.persist(staffRole);
		return staffRole;
	}

	@DeleteMapping("/staff-roles/{staffId}/{roleId}")
	public ResponseEntity<Void> deleteStaffRole(@PathVariable UUID staffId, @PathVariable Integer roleId) {
		StaffRoleId id = new StaffRoleId(staffId, roleId);
		StaffRole staffRole = findOrThrow(StaffRole.class, id, "StaffRole");
		entityManager.remove(staffRole);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/suppliers")
	public List<Supplier> listSuppliers() {
		return entityManager.createQuery("from Supplier", Supplier.class).getResultList();
	}

	@PostMapping("/suppliers")
	public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody SupplierRequest request) {
		Supplier supplier = new Supplier();
		applySupplier(supplier, request);
		entityManager.persist(supplier);
		return ResponseEntity.status(HttpStatus.CREATED).body(supplier);
	}

	@PutMapping("/suppliers/{id}")
	public Supplier updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierRequest request) {
		Supplier supplier = findOrThrow(Supplier.class, id, "Supplier");
		applySupplier(supplier, request);
		return supplier;
	}

	@DeleteMapping("/suppliers/{id}")
	public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
		Supplier supplier = findOrThrow(Supplier.class, id, "Supplier");
		entityManager.remove(supplier);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/tags")
	public List<Tag> listTags() {
		return entityManager.createQuery("from Tag", Tag.class).getResultList();
	}

	@PostMapping("/tags")
	public ResponseEntity<Tag> createTag(@Valid @RequestBody TagRequest request) {
		Tag tag = new Tag();
		tag.setTagName(request.tagName());
		tag.setIcon(request.icon());
		entityManager.persist(tag);
		return ResponseEntity.status(HttpStatus.CREATED).body(tag);
	}

	@PutMapping("/tags/{id}")
	public Tag updateTag(@PathVariable Integer id, @Valid @RequestBody TagRequest request) {
		Tag tag = findOrThrow(Tag.class, id, "Tag");
		tag.setTagName(request.tagName());
		tag.setIcon(request.icon());
		return tag;
	}

	@DeleteMapping("/tags/{id}")
	public ResponseEntity<Void> deleteTag(@PathVariable Integer id) {
		Tag tag = findOrThrow(Tag.class, id, "Tag");
		entityManager.remove(tag);
		return ResponseEntity.noContent().build();
	}

	private void applyStaffAccount(StaffAccount staff, StaffAccountRequest request, boolean requirePassword) {
		staff.setFirstName(request.firstName());
		staff.setLastName(request.lastName());
		staff.setPhoneNumber(request.phoneNumber());
		staff.setEmail(request.email());
		staff.setActive(request.active());
		staff.setProfileImg(request.profileImg());

		if (request.password() != null && !request.password().isBlank()) {
			staff.setPasswordHash(passwordEncoder.encode(request.password()));
		} else if (requirePassword) {
			throw new BadRequestException("Password is required.");
		}
	}

	private void applySupplier(Supplier supplier, SupplierRequest request) {
		supplier.setCompanyName(request.companyName());
		supplier.setContactName(request.contactName());
		supplier.setContactTitle(request.contactTitle());
		supplier.setAddress1(request.address1());
		supplier.setAddress2(request.address2());
		supplier.setCity(request.city());
		supplier.setState(request.state());
		supplier.setPostalCode(request.postalCode());
		supplier.setCountry(request.country());
		supplier.setPhone(request.phone());
		supplier.setFax(request.fax());
		supplier.setEmail(request.email());
		supplier.setUrl(request.url());
		supplier.setNotes(request.notes());
	}

	private <T, K> T findOrThrow(Class<T> type, K id, String label) {
		T entity = entityManager.find(type, id);
		if (entity == null) {
			throw new NotFoundException(label + " not found");
		}
		return entity;
	}
}
