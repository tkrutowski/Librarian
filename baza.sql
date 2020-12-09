
CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use library;


CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_user`));
  
  CREATE TABLE `authors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_author`));
  
  CREATE TABLE `bookstores`  (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL UNIQUE,
  `www` VARCHAR(100) NULL,
  PRIMARY KEY (`id_bookstore`));
  
    CREATE TABLE `series` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL UNIQUE,
  `description` TEXT NULL,
  PRIMARY KEY (`id_series`));
  
    CREATE TABLE `categories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL UNIQUE,
   PRIMARY KEY (`id_category`));
  
   CREATE TABLE `books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_series` BIGINT NULL,
  `title` VARCHAR(45) NULL,
  `subtitle` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `cover` VARCHAR(45) NULL,
  `book_in_series_no` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id_book`),
   FOREIGN KEY (id_series) REFERENCES series(id)
  );
  
	CREATE TABLE `books_categories` (
  `id_category`BIGINT NOT NULL,
  `id_book` BIGINT NOT NULL,
   PRIMARY KEY (id_book, id_category),
   FOREIGN KEY (id_book) REFERENCES books(id),
   FOREIGN KEY (id_category) REFERENCES categories(id)
   );
   
   	CREATE TABLE `books_authors` (
  `id_author` BIGINT NOT NULL,
  `id_book` BIGINT NOT NULL,
   PRIMARY KEY (id_book, id_author),
   FOREIGN KEY (id_book) REFERENCES books(id),
   FOREIGN KEY (id_author) REFERENCES authors(id)
   );
    
     CREATE TABLE `userbooks` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_book` BIGINT NOT NULL,
  `id_user` BIGINT NOT NULL,
  `id_bookstore` BIGINT NOT NULL,
  `edition_type` VARCHAR(20) NULL,
  `reading_status` VARCHAR(20) NULL,
  `ownership_status` VARCHAR(20) NULL,
  `read_from` DATE NULL,
  `read_to` DATE NULL,
  `info` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id_user) REFERENCES users(id),
  FOREIGN KEY (id_bookstore) REFERENCES bookstores(id)
  );
  
SELECT * from authors;
SELECT * from books;
delete from authors where id_author>0;

      INSERT INTO authors(first_name, last_name) VALUES('Lee','Child' );
    INSERT INTO authors(first_name, last_name) VALUES('Jo','Nesbo' );
    INSERT INTO authors(first_name, last_name) VALUES('Tom','Clancy' );
    INSERT INTO authors(first_name, last_name) VALUES('Ryk','Brown' );
    INSERT INTO authors(first_name, last_name) VALUES('Remigiusz','Mróz' );
    INSERT INTO authors(first_name, last_name) VALUES('Michael', 'Connelly' );
    INSERT INTO authors(first_name, last_name) VALUES('Evan', 'Currie' );
    
    select * from series;
    
      INSERT INTO users(username,password,name, role) VALUES('admin',/*1234*/'$2y$06$SWEQM5PNMs1psVa2FnRytuZvfeHO0Xnt2m0qXgGSPYPQnVBi5Z6Xy','administrator', 'ADMIN');
    INSERT INTO users(username,password,name, role) VALUES('test',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');
    INSERT INTO users(username,password,name, role) VALUES('Tomek',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');
    INSERT INTO users(username,password,name, role) VALUES('Paweł',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');
