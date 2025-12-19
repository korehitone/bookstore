SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


-- CREATE DATABASE

CREATE DATABASE IF NOT EXISTS bookstore;
USE bookstore;

-- CREATE TABLE

CREATE TABLE `category` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL
);


CREATE TABLE `book` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `uid` VARCHAR(36) NOT NULL,
    `category_id` INT(11) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `author` VARCHAR(255) NOT NULL,
    `publisher` VARCHAR(64) NOT NULL,
    `release_date` DATE NOT NULL,
    `sipnosis` VARCHAR(255) NOT NULL,
    `img_url` TEXT NULL,
    `price` INT(11) NOT NULL DEFAULT 0,
    FOREIGN KEY (`category_id`) REFERENCES `category`(`id`) ON DELETE CASCADE
);

CREATE TABLE `customer` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `uid` VARCHAR(36) NOT NULL,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `email` VARCHAR(64) NOT NULL UNIQUE,
    `password` VARCHAR(64) NOT NULL,
    `address` VARCHAR(255) NULL
);

CREATE TABLE `admin` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL,
    `email` VARCHAR(64) NOT NULL,
    `password` VARCHAR(64) NOT NULL
);

CREATE TABLE `cart` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `uid` VARCHAR(36) NOT NULL,
    `customer_id` INT(11) NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`) ON DELETE CASCADE
);

CREATE TABLE `category_book` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `book_id` INT(11) NOT NULL,
    `category_id` INT(11) NOT NULL,
    FOREIGN KEY (`book_id`) REFERENCES `book`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`category_id`) REFERENCES `category`(`id`) ON DELETE CASCADE
);

CREATE TABLE `book_cart` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `cart_id` INT(11) NOT NULL,
    `book_id` INT(11) NOT NULL,
    `quantity` INT(11) NOT NULL DEFAULT 1,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`cart_id`) REFERENCES `cart`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`book_id`) REFERENCES `book`(`id`) ON DELETE CASCADE
);

-- INSERT VALUES

-- INSERT CATEGORY 
INSERT INTO `category` (`name`) VALUES
('Light Novel'),
('Children Book'),
('Comics'),
('Non-Fiction'),
('Fiction');

-- INSERT BOOK
-- INSERT INTO `book` (`category_id`, `title`, `author`, `sipnosis`, `img_url`, `price`) VALUES
-- (1, 'Sword Art Online Vol. 1: Aincrad', 'Reki Kawahara', 'In the year 2022, gamers rejoice as Sword Art Online launches. But when tens of thousands log in, they discover they cannot log out. The only way to escape is to beat all 100 floors of the deadly game.', 'https://example.com/sao.jpg', 120000),
-- (2, 'The Very Hungry Caterpillar', 'Eric Carle', 'A small caterpillar eats his way through various foods before transforming into a beautiful butterfly. A classic picture book loved by children worldwide.', 'https://example.com/caterpillar.jpg', 85000),
-- (3, 'One Piece Vol. 1', 'Eiichiro Oda', 'Monkey D. Luffy sets sail to find the legendary treasure One Piece and become King of the Pirates. His adventure begins with forming his first crew.', 'https://example.com/onepiece.jpg', 95000),
-- (4, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'An exploration of how Homo sapiens came to dominate the world, examining biology, history, and the future of our species.', 'https://example.com/sapiens.jpg', 180000),
-- (5, 'The Hunger Games', 'Suzanne Collins', 'In a dystopian future, Katniss Everdeen volunteers to take her sisters place in a deadly televised competition where teenagers fight to the death.', 'https://example.com/hungergames.jpg', 110000);

INSERT INTO book (uid, category_id, title, author, publisher, release_date, sipnosis, img_url, price) VALUES
('f47ac10b-58cc-4372-a567-0e02b2c3d470', 1, 'Sword Art Online Vol. 1: Aincrad', 'Reki Kawahara', 'ASCII Media Works', '2012-04-10',
 'In the year 2022, gamers rejoice as Sword Art Online launches. But when tens of thousands log in, they discover they cannot log out. The only way to escape is to beat all 100 floors of the deadly game.',
 'https://example.com/sao.jpg', 120000),
('f47ac10b-58cc-4372-a567-0e02b2c3d471', 2, 'The Very Hungry Caterpillar', 'Eric Carle', 'World Publishing Company', '1969-06-03',
 'A small caterpillar eats his way through various foods before transforming into a beautiful butterfly. A classic picture book loved by children worldwide.',
 'https://example.com/caterpillar.jpg', 85000),
('f47ac10b-58cc-4372-a567-0e02b2c3d472', 3, 'One Piece Vol. 1', 'Eiichiro Oda', 'Shueisha', '1997-12-24',
 'Monkey D. Luffy sets sail to find the legendary treasure One Piece and become King of the Pirates. His adventure begins with forming his first crew.',
 'https://example.com/onepiece.jpg', 95000),
('f47ac10b-58cc-4372-a567-0e02b2c3d473', 4, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'Harvill Secker', '2011-01-01',
 'An exploration of how Homo sapiens came to dominate the world, examining biology, history, and the future of our species.',
 'https://example.com/sapiens.jpg', 180000),
('f47ac10b-58cc-4372-a567-0e02b2c3d474', 5, 'The Hunger Games', 'Suzanne Collins', 'Scholastic Press', '2008-09-14',
 'In a dystopian future, Katniss Everdeen volunteers to take her sisters place in a deadly televised competition where teenagers fight to the death.',
 'https://example.com/hungergames.jpg', 110000);


-- INSERT CUSTOMER 
INSERT INTO `customer` (`uid`, `username`, `email`, `password`, `address`) VALUES
('6ba7b810-9dad-11d1-80b4-00c04fd430c0','johndoe', 'john.doe@email.com', 'hashed_password_123', 'Jl. Sudirman No. 123, Jakarta'),
('6ba7b810-9dad-11d1-80b4-00c04fd430c1','janesmth', 'jane.smith@email.com', 'hashed_password_456', 'Jl. Thamrin No. 45, Jakarta'),
('6ba7b810-9dad-11d1-80b4-00c04fd430c2','bookworm88', 'bookworm88@email.com', 'hashed_password_abc', 'Jl. Asia Afrika No. 78, Bandung'),
('6ba7b810-9dad-11d1-80b4-00c04fd430c3','reader_pro', 'readerpro@email.com', 'hashed_password_xyz', 'Jl. Malioboro No. 56, Yogyakarta'),
('6ba7b810-9dad-11d1-80b4-00c04fd430c4','bookfan21', 'bookfan21@email.com', 'hashed_password_def', 'Jl. Diponegoro No. 99, Surabaya');

-- INSERT ADMIN 
INSERT INTO `admin` (`username`, `email`, `password`) VALUES
('admin_master', 'admin@bookstore.com', 'hashed_admin_pass_001'),
('super_admin', 'superadmin@bookstore.com', 'hashed_admin_pass_002'),
('manager_store', 'manager@bookstore.com', 'hashed_admin_pass_003'),
('moderator01', 'mod01@bookstore.com', 'hashed_admin_pass_004'),
('admin_support', 'support@bookstore.com', 'hashed_admin_pass_005');

-- INSERT CART
INSERT INTO `cart` (`uid` ,`customer_id`) VALUES
('550e8400-e29b-41d4-a716-446655440000',1),
('550e8400-e29b-41d4-a716-446655440001',2),
('550e8400-e29b-41d4-a716-446655440002',3),
('550e8400-e29b-41d4-a716-446655440003',4),
('550e8400-e29b-41d4-a716-446655440000',5);

-- INSERT CATEGORY-BOOK 
INSERT INTO `category_book` (`book_id`, `category_id`) VALUES
(1, 1),  -- SAO -> Light Novel
(2, 2),  -- Caterpillar -> Children Book
(3, 3),  -- One Piece -> Comics
(4, 4),  -- Sapiens -> Non-Fiction
(5, 5);  -- Hunger Games -> Fiction

-- INSERT BOOK-CART
INSERT INTO `book_cart` (`cart_id`, `book_id`, `quantity`) VALUES
(1, 1, 1),  -- Cart 1: SAO (120000)
(1, 3, 1),  -- Cart 1: One Piece (95000) = Total: 215000
(2, 4, 1),  -- Cart 2: Sapiens (180000)
(3, 3, 1),  -- Cart 3: One Piece (95000)
(4, 2, 1),  -- Cart 4: Caterpillar (85000)
(4, 1, 1),  -- Cart 4: SAO (120000) = Total: 205000
(5, 2, 1);  -- Cart 5: Caterpillar (85000)