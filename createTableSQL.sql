DROP DATABASE IF EXISTS `librarysystem`;

CREATE DATABASE `librarysystem` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE `librarysystem`;

CREATE TABLE `reader` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `librarian` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `author` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `description` text COLLATE utf8mb4_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `book` (
  `isbn` varchar(13) COLLATE utf8mb4_bin NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `title` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `description` text COLLATE utf8mb4_bin,
  `publisher` varchar(50),
  `year` int,
  `aid` int(10) unsigned not null,
  `popularity` int,
  PRIMARY KEY (`isbn`),
  FOREIGN KEY (`aid`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `book_in_library` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `isbn` varchar(13) COLLATE utf8mb4_bin NOT NULL,
  `location` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `state` varchar(9) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `cart` (
    `id` int not null,
    `reader_id` int(10) unsigned NOT NULL,
    `submit_time`datetime,
    `stat` VARCHAR(10) not null,
    PRIMARY KEY (`id`),
  CONSTRAINT `borrow_cart_ibfk_2` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `reserve_books` (
     `reserve_id` int not null,
     `book_id` varchar(13) COLLATE utf8mb4_bin not null,
     FOREIGN KEY (`reserve_id`) REFERENCES `cart` (`id`),
     FOREIGN KEY (`book_id`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `borrow_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `reader_id` int(10) unsigned NOT NULL,
  `book_id` int(10) unsigned NOT NULL,
  `borrow_librarian_id` int(10) unsigned NOT NULL,
  `return_librarian_id` int(10) unsigned,
  `borrow_time` datetime NOT NULL,
  `return_time` datetime,
  PRIMARY KEY (`id`),
  CONSTRAINT `borrow_item_ibfk_1` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`),
  CONSTRAINT `borrow_item_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book_in_library` (`id`),
  CONSTRAINT `borrow_item_ibfk_3` FOREIGN KEY (`borrow_librarian_id`) REFERENCES `librarian` (`id`),
  CONSTRAINT `borrow_item_ibfk_4reader` FOREIGN KEY (`return_librarian_id`) REFERENCES `librarian` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;