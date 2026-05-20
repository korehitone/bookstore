# Bookstore REST API

Online bookstore REST API built with Spring Boot 4.0.0. Provides complete CRUD operations for managing books, customers, carts, categories, and admin users with Swagger/OpenAPI documentation.

Version: 0.0.1-SNAPSHOT
Language: Java
Framework: Spring Boot 4.0.0

---

## Features

### Book Management
- List all books with pagination
- Get book details by UID
- Search books by title
- Filter books by category
- Add new books
- Update book information
- Delete books

### Shopping Cart
- Create customer cart
- Add books to cart
- View cart items with pagination
- Update cart item quantity
- Remove items from cart
- Calculate cart total price

### User Management
- Customer registration (sign up)
- Customer login with password verification
- Get customer profile
- Update customer profile
- Delete customer account
- Admin registration
- Admin login
- Admin profile management

### Category Management
- List all categories
- Get category details
- Create new categories
- Update category names
- Delete categories

---

## Technology Stack

### Backend Framework
- Spring Boot 4.0.0 - Rest API framework
- Spring Data JPA - Database ORM
- Spring Security - Password encryption (BCrypt)
- Spring Data REST - REST support

### Database
- MySQL - Database engine
- Hibernate - ORM provider
- JDBC - Database driver

### API Documentation
- Spring Doc OpenAPI 3.0.0 - Swagger integration
- Swagger UI - Interactive API documentation

### Build Tool
- Maven 4.0 - Dependency management
- Java 25 - Programming language

---

## Database Schema

### Tables and Relationships

#### category
- id: INT (Primary Key, Auto Increment)
- name: VARCHAR(50)

#### book
- id: INT (Primary Key, Auto Increment)
- uid: VARCHAR(36) (UUID)
- category_id: INT (Foreign Key -> category.id)
- title: VARCHAR(255)
- author: VARCHAR(255)
- publisher: VARCHAR(64)
- release_date: DATE
- sipnosis: VARCHAR(255)
- img_url: TEXT (nullable)
- price: INT

#### customer
- id: INT (Primary Key, Auto Increment)
- uid: VARCHAR(36) (UUID)
- username: VARCHAR(50) (UNIQUE)
- email: VARCHAR(64) (UNIQUE)
- password: VARCHAR(64) (BCrypt hashed)
- address: VARCHAR(255) (nullable)

#### admin
- id: INT (Primary Key, Auto Increment)
- username: VARCHAR(50)
- email: VARCHAR(64)
- password: VARCHAR(64) (BCrypt hashed)

#### cart
- id: INT (Primary Key, Auto Increment)
- uid: VARCHAR(36) (UUID)
- customer_id: INT (Foreign Key -> customer.id)
- created_at: DATETIME (Auto set)
- updated_at: DATETIME (Auto set)

#### book_cart (Join Table)
- id: INT (Primary Key, Auto Increment)
- cart_id: INT (Foreign Key -> cart.id)
- book_id: INT (Foreign Key -> book.id)
- quantity: INT (Default 1)
- created_at: DATETIME (Auto set)
- updated_at: DATETIME (Auto set)

#### category_book
- id: INT (Primary Key, Auto Increment)
- book_id: INT (Foreign Key -> book.id)
- category_id: INT (Foreign Key -> category.id)

---

## API Endpoints

### Book Endpoints

GET /api/book
Get all books (paginated)
Query Parameters: page (default 0), size (default 25)

GET /api/book/{uid}
Get book details by UID
Path Parameters: uid - Book UID

GET /api/book/category
Search books by category
Query Parameters: category (required), page, size

GET /api/book/search
Search books by title
Query Parameters: title (required), page, size

POST /api/book
Create new book
Request Body: Book object (categoryId, title, author, publisher, releaseDate, sipnosis, imgUrl, price)

PUT /api/book/{uid}
Update book
Path Parameters: uid - Book UID
Request Body: Updated Book object

DELETE /api/book/{uid}
Delete book
Path Parameters: uid - Book UID

---

### Cart Endpoints

GET /api/cart
Get customer cart
Query Parameters: uid (customer uid)

GET /api/cart/item
Get cart items (paginated)
Query Parameters: cuid (cart uid), page, size

POST /api/cart/item
Add item to cart
Request Body: BookCart object (cartId, bookId, quantity)

PUT /api/cart/item
Update cart item quantity
Request Body: BookCart object (id, quantity)

DELETE /api/cart/item/{id}
Remove item from cart
Path Parameters: id - BookCart item ID

---

### Customer/User Endpoints

GET /api/user/{uid}
Get customer profile
Path Parameters: uid - Customer UID

POST /api/user
Register new customer (Sign Up)
Request Body: Customer object (username, email, password, address)

GET /api/user/login
Login customer
Request Headers: email, password

PUT /api/user/{uid}
Update customer profile
Path Parameters: uid - Customer UID
Request Body: Customer object (username, address)

DELETE /api/user/{uid}
Delete customer account
Path Parameters: uid - Customer UID

---

### Admin Endpoints

GET /api/admin/{id}
Get admin profile
Path Parameters: id - Admin ID

POST /api/admin
Register new admin
Request Body: Admin object (username, email, password)

GET /api/admin/login
Login admin
Request Headers: email, password

PUT /api/admin/{id}
Update admin profile
Path Parameters: id - Admin ID
Request Body: Admin object (username, email)

DELETE /api/admin/{id}
Delete admin
Path Parameters: id - Admin ID

---

### Category Endpoints

GET /api/category
Get all categories

GET /api/category/{id}
Get category details
Path Parameters: id - Category ID

POST /api/category
Create new category
Request Body: Category object (name)

PUT /api/category/{id}
Update category
Path Parameters: id - Category ID
Request Body: Category object (name)

DELETE /api/category/{id}
Delete category
Path Parameters: id - Category ID
