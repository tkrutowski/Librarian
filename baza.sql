
CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use library;


CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `authors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `bookstores`  (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL UNIQUE,
  `url` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));
  
    CREATE TABLE `series` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL UNIQUE,
  `description` TEXT NULL,
  PRIMARY KEY (`id`));
  
    CREATE TABLE `categories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL UNIQUE,
   PRIMARY KEY (`id`));
  
   CREATE TABLE `books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_series` BIGINT NULL,
  `title` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `cover` TEXT NULL,
  `book_in_series_no` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
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
  `edition_type` VARCHAR(20) NOT NULL,
  `reading_status` VARCHAR(20) NOT NULL,
  `ownership_status` VARCHAR(20) NOT NULL,
  `read_from` DATE NULL,
  `read_to` DATE NULL,
  `info` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id_book) REFERENCES books(id),
  FOREIGN KEY (id_user) REFERENCES users(id),
  FOREIGN KEY (id_bookstore) REFERENCES bookstores(id)
  );
  INSERT INTO users(username,password,name, role) VALUES('admin',/*1234*/'$2y$06$SWEQM5PNMs1psVa2FnRytuZvfeHO0Xnt2m0qXgGSPYPQnVBi5Z6Xy','administrator', 'ADMIN');
SELECT * from authors;
SELECT * from authors where first_name='rYK';
SELECT * from users;
SELECT * from books;

SELECT * from series;
SELECT * from userbooks;
SELECT * FROM categories;
delete from authors where id_author>0;

update categories set name = 'Sensacja' where id=5;
      INSERT INTO authors(first_name, last_name) VALUES('Lee','Child' );
    INSERT INTO authors(first_name, last_name) VALUES('Jo','Nesbo' );
    INSERT INTO authors(first_name, last_name) VALUES('Tom','Clancy' );
    INSERT INTO authors(first_name, last_name) VALUES('Ryk','Brown' );
    INSERT INTO authors(first_name, last_name) VALUES('Remigiusz','Mróz' );
    INSERT INTO authors(first_name, last_name) VALUES('Michael', 'Connelly' );
    INSERT INTO authors(first_name, last_name) VALUES('Evan', 'Currie' );
    
    select * from categories;
    select * from categories where name='kryminał';
    delete from categories where id=5;
    
    delete from books where id=16;
      INSERT INTO users(username,password,name, role) VALUES('admin',/*1234*/'$2y$06$SWEQM5PNMs1psVa2FnRytuZvfeHO0Xnt2m0qXgGSPYPQnVBi5Z6Xy','administrator', 'ADMIN');
    INSERT INTO users(username,password,name, role) VALUES('test',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');
    INSERT INTO users(username,password,name, role) VALUES('Tomek',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');
    INSERT INTO users(username,password,name, role) VALUES('Paweł',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');
 
 INSERT INTO series(title, description) VALUES('Jack Reacher','dsfsdfs dfsfds ds sd s' );
       INSERT INTO series(title, description) VALUES('The Frontires Saga','d55435 grtet sfsdfs dfsfds ds sd s' );
       INSERT INTO series(title, description) VALUES('Odyssey One','d55435 grtet sfsdfs dfsfds ds sd s' );
       
        INSERT INTO bookstores(name, url) VALUES('Empik','www.empik.com' );
       INSERT INTO bookstores(name, url) VALUES('PWN','www.pwn.pl' );
       
             INSERT INTO categories(name) VALUES('Thriller');
        INSERT INTO categories(name) VALUES('Sensacja');
        INSERT INTO categories(name) VALUES('Fantastyka');
        INSERT INTO categories(name) VALUES('Science fiction');
        INSERT INTO categories(name) VALUES('Kryminał');
        https://upolujebooka.pl/_data_cache/_data/offer/013/190_290_0_0_0_1_128754-legion_niesmiertelnych_3_swiat_postepu.jpg
        
        INSERT INTO books(id_series,title,subtitle,description,cover,book_in_series_no)
    VALUES(33,'Odyssey One', 'Ostatni bastion','Trzeci tom pasjonującego cyklu SF Evana Curie.','https://upolujebooka.pl/_data_cache/_data/offer/005/190_290_0_0_0_1_47751-odyssey_one_tom_3.jpg', 3);
    insert into books_authors values (17,7);
    insert into books_categories values (48,17);
    insert into books_categories values (49,17);
       