-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.1-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for inventory
CREATE DATABASE IF NOT EXISTS `inventory` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `inventory`;

-- Dumping structure for table inventory.order_entity
CREATE TABLE IF NOT EXISTS `order_entity` (
                                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                              `created` datetime(6) NOT NULL DEFAULT current_timestamp(6),
                                              `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table inventory.order_entity_products
CREATE TABLE IF NOT EXISTS `order_entity_products` (
                                                       `order_entity_id` bigint(20) NOT NULL,
                                                       `products_id` bigint(20) NOT NULL,
                                                       KEY `FK6yc85tyii1ucsw89c3loun2xa` (`products_id`),
                                                       KEY `FKg55cfg2q01u24symgl4klax6v` (`order_entity_id`),
                                                       CONSTRAINT `FK6yc85tyii1ucsw89c3loun2xa` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`),
                                                       CONSTRAINT `FKg55cfg2q01u24symgl4klax6v` FOREIGN KEY (`order_entity_id`) REFERENCES `order_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table inventory.product
CREATE TABLE IF NOT EXISTS `product` (
                                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                         `created` datetime(6) NOT NULL DEFAULT current_timestamp(6),
                                         `deleted` bit(1) NOT NULL DEFAULT b'0',
                                         `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                         `price` decimal(19,2) DEFAULT NULL,
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
