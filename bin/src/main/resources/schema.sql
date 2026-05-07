CREATE TABLE IF NOT EXISTS users (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password_hash VARCHAR(100) NOT NULL,
	created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS suppliers (
	id BIGSERIAL PRIMARY KEY,
	company_name VARCHAR(255) NOT NULL,
	contact_name VARCHAR(255),
	contact_title VARCHAR(255),
	address1 VARCHAR(255),
	address2 VARCHAR(255),
	city VARCHAR(255),
	state VARCHAR(255),
	postal_code VARCHAR(50),
	country VARCHAR(255),
	phone VARCHAR(100),
	fax VARCHAR(100),
	email VARCHAR(255),
	url VARCHAR(255),
	notes TEXT
);

CREATE TABLE IF NOT EXISTS categories (
	id BIGSERIAL PRIMARY KEY,
	parent_id BIGINT,
	category_name VARCHAR(255) NOT NULL,
	description TEXT,
	icon TEXT,
	picture TEXT,
	active BOOLEAN,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE TABLE IF NOT EXISTS tags (
	id SERIAL PRIMARY KEY,
	tag_name VARCHAR(255),
	icon TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE TABLE IF NOT EXISTS attributes (
	id UUID PRIMARY KEY,
	attribute_name VARCHAR(255),
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE TABLE IF NOT EXISTS attribute_values (
	id UUID PRIMARY KEY,
	attribute_id UUID,
	attribute_value VARCHAR(255),
	color VARCHAR(50),
	CONSTRAINT fk_attribute_values_attribute
		FOREIGN KEY (attribute_id) REFERENCES attributes(id)
);

CREATE TABLE IF NOT EXISTS products (
	id BIGSERIAL PRIMARY KEY,
	product_name VARCHAR(255) NOT NULL,
	sku VARCHAR(255),
	regular_price NUMERIC,
	discount_price NUMERIC,
	quantity INT,
	short_description VARCHAR(165),
	product_description TEXT,
	product_weight NUMERIC,
	product_note VARCHAR(255),
	published BOOLEAN,
	unit_price NUMERIC(12, 2),
	units_in_stock INT,
	supplier_id BIGINT,
	category_id BIGINT,
	idsku VARCHAR(255),
	vendor_product_id VARCHAR(255),
	quantity_per_unit VARCHAR(255),
	msrp NUMERIC(12, 2),
	available_size VARCHAR(255),
	available_colors VARCHAR(255),
	size VARCHAR(255),
	color VARCHAR(255),
	discount NUMERIC(8, 2),
	unit_weight NUMERIC(8, 2),
	units_on_order INT,
	reorder_level INT,
	product_available BOOLEAN,
	discount_available BOOLEAN,
	current_order BOOLEAN,
	picture VARCHAR(255),
	product_gallery TEXT,
	ranking INT,
	note TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID,
	CONSTRAINT fk_products_supplier
		FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
	CONSTRAINT fk_products_category
		FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS product_tags (
	tag_id INT NOT NULL,
	product_id BIGINT NOT NULL,
	PRIMARY KEY (tag_id, product_id),
	CONSTRAINT fk_product_tags_tag
		FOREIGN KEY (tag_id) REFERENCES tags(id),
	CONSTRAINT fk_product_tags_product
		FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS product_categories (
	category_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	PRIMARY KEY (category_id, product_id),
	CONSTRAINT fk_product_categories_category
		FOREIGN KEY (category_id) REFERENCES categories(id),
	CONSTRAINT fk_product_categories_product
		FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS product_attributes (
	product_id BIGINT NOT NULL,
	attribute_id UUID NOT NULL,
	PRIMARY KEY (product_id, attribute_id),
	CONSTRAINT fk_product_attributes_product
		FOREIGN KEY (product_id) REFERENCES products(id),
	CONSTRAINT fk_product_attributes_attribute
		FOREIGN KEY (attribute_id) REFERENCES attributes(id)
);

CREATE TABLE IF NOT EXISTS variants (
	id UUID PRIMARY KEY,
	variant_attribute_value_id UUID,
	product_id BIGINT,
	CONSTRAINT fk_variants_product
		FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS variant_values (
	id UUID PRIMARY KEY,
	variant_id UUID,
	price NUMERIC,
	quantity INT,
	CONSTRAINT fk_variant_values_variant
		FOREIGN KEY (variant_id) REFERENCES variants(id)
);

CREATE TABLE IF NOT EXISTS variant_attribute_values (
	id UUID PRIMARY KEY,
	variant_attribute_value_id UUID,
	attribute_value_id UUID,
	CONSTRAINT fk_variant_attribute_values_attribute_value
		FOREIGN KEY (attribute_value_id) REFERENCES attribute_values(id)
);

CREATE TABLE IF NOT EXISTS coupons (
	id SERIAL PRIMARY KEY,
	code VARCHAR(255),
	coupon_description TEXT,
	discount_value NUMERIC,
	discount_type VARCHAR(50),
	times_used INT,
	max_usage INT,
	coupon_start_date TIMESTAMP,
	coupon_end_date TIMESTAMP,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE TABLE IF NOT EXISTS product_coupons (
	coupon_id INT NOT NULL,
	product_id BIGINT NOT NULL,
	PRIMARY KEY (coupon_id, product_id),
	CONSTRAINT fk_product_coupons_coupon
		FOREIGN KEY (coupon_id) REFERENCES coupons(id),
	CONSTRAINT fk_product_coupons_product
		FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS galleries (
	id UUID PRIMARY KEY,
	product_id BIGINT,
	image_path TEXT,
	thumbnail BOOLEAN,
	display_order SMALLINT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID,
	CONSTRAINT fk_galleries_product
		FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS sells (
	id UUID PRIMARY KEY,
	product_id BIGINT,
	price FLOAT,
	quantity SMALLINT,
	CONSTRAINT fk_sells_product
		FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS shippings (
	id SERIAL PRIMARY KEY,
	name TEXT,
	active BOOLEAN,
	icon_path TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE TABLE IF NOT EXISTS product_shippings (
	product_id BIGINT NOT NULL,
	shipping_id INT NOT NULL,
	ship_charge NUMERIC,
	free BOOLEAN,
	estimated_days NUMERIC,
	PRIMARY KEY (product_id, shipping_id),
	CONSTRAINT fk_product_shippings_product
		FOREIGN KEY (product_id) REFERENCES products(id),
	CONSTRAINT fk_product_shippings_shipping
		FOREIGN KEY (shipping_id) REFERENCES shippings(id)
);

CREATE TABLE IF NOT EXISTS customers (
	id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(100),
	phone_number VARCHAR(255),
	email TEXT,
	password_hash TEXT,
	active BOOLEAN,
	registered_at TIMESTAMP,
	created_at TIMESTAMP,
	class VARCHAR(255),
	room VARCHAR(255),
	building VARCHAR(255),
	address1 TEXT,
	address2 TEXT,
	city VARCHAR(255),
	state VARCHAR(255),
	postal_code VARCHAR(50),
	country VARCHAR(255),
	phone VARCHAR(100),
	voice_mail VARCHAR(255),
	password VARCHAR(255),
	credit_card VARCHAR(255),
	credit_card_type_id VARCHAR(255),
	card_exp_mo VARCHAR(50),
	card_exp_yr VARCHAR(50),
	billing_address TEXT,
	billing_city VARCHAR(255),
	billing_region VARCHAR(255),
	billing_postal_code VARCHAR(50),
	billing_country VARCHAR(255),
	ship_address TEXT,
	ship_city VARCHAR(255),
	ship_region VARCHAR(255),
	ship_postal_code VARCHAR(50),
	ship_country VARCHAR(255),
	address TEXT
);

CREATE TABLE IF NOT EXISTS customer_addresses (
	id UUID PRIMARY KEY,
	customer_id BIGINT,
	address_line1 TEXT,
	address_line2 TEXT,
	postal_code VARCHAR(255),
	country VARCHAR(255),
	city VARCHAR(255),
	phone_number VARCHAR(255),
	CONSTRAINT fk_customer_addresses_customer
		FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS cards (
	card_id UUID PRIMARY KEY,
	customer_id BIGINT,
	CONSTRAINT fk_cards_customer
		FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS card_items (
	id UUID PRIMARY KEY,
	card_id UUID,
	product_id BIGINT,
	quantity SMALLINT,
	CONSTRAINT fk_card_items_card
		FOREIGN KEY (card_id) REFERENCES cards(card_id),
	CONSTRAINT fk_card_items_product
		FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS order_statuses (
	id SERIAL PRIMARY KEY,
	status_name VARCHAR(255),
	color VARCHAR(50),
	privacy VARCHAR(50),
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE TABLE IF NOT EXISTS payment (
	id BIGSERIAL PRIMARY KEY,
	payment_type VARCHAR(255) NOT NULL,
	allowed BOOLEAN
);

CREATE TABLE IF NOT EXISTS shippers (
	id BIGSERIAL PRIMARY KEY,
	company_name VARCHAR(255) NOT NULL,
	phone VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS orders (
	id BIGSERIAL PRIMARY KEY,
	coupon_id INT,
	customer_id BIGINT,
	order_status_id INT,
	order_approved_at TIMESTAMP,
	order_delivered_carrier_date TIMESTAMP,
	order_delivered_customer_date TIMESTAMP,
	status VARCHAR(50),
	total NUMERIC(12, 2),
	order_number VARCHAR(255),
	payment_id BIGINT,
	order_date TIMESTAMP,
	ship_date TIMESTAMP,
	required_date TIMESTAMP,
	shipper_id BIGINT,
	freight NUMERIC(12, 2),
	sales_tax NUMERIC(12, 2),
	timestamp TIMESTAMP,
	transact_status VARCHAR(255),
	err_loc VARCHAR(255),
	err_msg TEXT,
	fulfilled BOOLEAN,
	deleted BOOLEAN,
	paid BOOLEAN,
	payment_date TIMESTAMP,
	created_at TIMESTAMP,
	CONSTRAINT fk_orders_coupon
		FOREIGN KEY (coupon_id) REFERENCES coupons(id),
	CONSTRAINT fk_orders_customer
		FOREIGN KEY (customer_id) REFERENCES customers(id),
	CONSTRAINT fk_orders_status
		FOREIGN KEY (order_status_id) REFERENCES order_statuses(id),
	CONSTRAINT fk_orders_payment
		FOREIGN KEY (payment_id) REFERENCES payment(id),
	CONSTRAINT fk_orders_shipper
		FOREIGN KEY (shipper_id) REFERENCES shippers(id)
);

CREATE TABLE IF NOT EXISTS order_items (
	id BIGSERIAL PRIMARY KEY,
	product_id BIGINT,
	order_id BIGINT,
	price NUMERIC,
	quantity INT,
	shipping_id INT,
	total NUMERIC(12, 2),
	discount NUMERIC(8, 2),
	idsku VARCHAR(255),
	size VARCHAR(255),
	color VARCHAR(255),
	fulfilled BOOLEAN,
	ship_date TIMESTAMP,
	bill_date TIMESTAMP,
	CONSTRAINT fk_order_items_product
		FOREIGN KEY (product_id) REFERENCES products(id),
	CONSTRAINT fk_order_items_order
		FOREIGN KEY (order_id) REFERENCES orders(id),
	CONSTRAINT fk_order_items_shipping
		FOREIGN KEY (shipping_id) REFERENCES shippings(id)
);

CREATE TABLE IF NOT EXISTS roles (
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(255),
	privileges TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE TABLE IF NOT EXISTS staff_accounts (
	id UUID PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(100),
	phone_number VARCHAR(255),
	email VARCHAR(255),
	password_hash TEXT,
	active BOOLEAN,
	profile_img TEXT,
	registered_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE TABLE IF NOT EXISTS staff_roles (
	staff_id UUID NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY (staff_id, role_id),
	CONSTRAINT fk_staff_roles_staff
		FOREIGN KEY (staff_id) REFERENCES staff_accounts(id),
	CONSTRAINT fk_staff_roles_role
		FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS notifications (
	id UUID PRIMARY KEY,
	account_id UUID,
	title VARCHAR(100),
	content TEXT,
	seen BOOLEAN,
	created_at TIMESTAMP,
	receive_time TIME,
	notification_expiry_date DATE,
	CONSTRAINT fk_notifications_account
		FOREIGN KEY (account_id) REFERENCES staff_accounts(id)
);

CREATE TABLE IF NOT EXISTS slideshows (
	id UUID PRIMARY KEY,
	destination_url TEXT,
	image_url TEXT,
	clicks SMALLINT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	created_by UUID,
	updated_by UUID
);

CREATE OR REPLACE VIEW product_shipping AS
	SELECT * FROM product_shippings;

CREATE OR REPLACE VIEW products_attributes AS
	SELECT * FROM product_attributes;

CREATE OR REPLACE VIEW cart_items AS
	SELECT * FROM card_items;

CREATE OR REPLACE VIEW notification AS
	SELECT * FROM notifications;

CREATE OR REPLACE VIEW slideshow AS
	SELECT * FROM slideshows;

CREATE OR REPLACE VIEW attribute AS
	SELECT * FROM attributes;

CREATE OR REPLACE VIEW "values" AS
	SELECT * FROM attribute_values;

CREATE OR REPLACE VIEW "order" AS
	SELECT * FROM orders;
