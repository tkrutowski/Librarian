INSERT INTO users(username,password,name, role) VALUES('admin',/*1234*/'$2y$06$SWEQM5PNMs1psVa2FnRytuZvfeHO0Xnt2m0qXgGSPYPQnVBi5Z6Xy','administrator', 'ADMIN');
    INSERT INTO users(username,password,name, role) VALUES('test',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');
    INSERT INTO users(username,password,name, role) VALUES('Tomek',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');
    INSERT INTO users(username,password,name, role) VALUES('Paweł',/*'12345'*/'$2y$06$ACSryLuz9ojOffj/.ADPquYUHGTgoPq6mjXF24iAWG6YOvV5LYA2W','tom kru','USER');

      INSERT INTO categories(name) VALUES('Thriller');
        INSERT INTO categories(name) VALUES('Sensacja');
        INSERT INTO categories(name) VALUES('Fantastyka');
        INSERT INTO categories(name) VALUES('Science fiction');
        INSERT INTO categories(name) VALUES('Kryminał');


          INSERT INTO authors(first_name, last_name) VALUES('Lee','Child' );
    INSERT INTO authors(first_name, last_name) VALUES('Jo','Nesbo' );
    INSERT INTO authors(first_name, last_name) VALUES('Tom','Clancy' );
    INSERT INTO authors(first_name, last_name) VALUES('Ryk','Brown' );
    INSERT INTO authors(first_name, last_name) VALUES('Remigiusz','Mróz' );
    INSERT INTO authors(first_name, last_name) VALUES('Michael', 'Connelly' );
    INSERT INTO authors(first_name, last_name) VALUES('Evan', 'Currie' );


       INSERT INTO series(title, description) VALUES('Jack Reacher','dsfsdfs dfsfds ds sd s' );
       INSERT INTO series(title, description) VALUES('The Frontires Saga','d55435 grtet sfsdfs dfsfds ds sd s' );
       INSERT INTO series(title, description) VALUES('Odyssey One','d55435 grtet sfsdfs dfsfds ds sd s' );

       INSERT INTO bookstores(name, url) VALUES('Empik','www.empik.com' );
       INSERT INTO bookstores(name, url) VALUES('PWN','www.pwn.pl' );


    INSERT INTO books(id_series,title,subtitle,description,cover,book_in_series_no)
    VALUES(3,'Odyssey One', 'Ostatni bastion','Trzeci tom pasjonującego cyklu SF Evana Curie.','https://upolujebooka.pl/_data_cache/_data/offer/005/190_290_0_0_0_1_47751-odyssey_one_tom_3.jpg', 3);
    insert into books_authors values (1,7);
    insert into books_categories values (1,3);
    insert into books_categories values (1,4);

--
--       INSERT INTO books(id_series,title,subtitle,description,cover,book_in_series_no)
--     VALUES(3,'Odyssey One', 'W ogniu wojny','Trzeci tom pasjonującego cyklu SF Evana Curie.','adresURL_inny', 4);
--       INSERT INTO books(id_series,title,subtitle,description,cover,book_in_series_no)
--     VALUES(3,'Odyssey One', 'Król Wojowników','Trzeci tom pasjonującego cyklu SF Evana Curie.','adresURL_jesczczeInny', 5);
