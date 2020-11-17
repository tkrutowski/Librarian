   INSERT INTO users(login,password,name, role) VALUES('admin','1234','administrator', 'ADMIN');
    INSERT INTO users(login,password,name, role) VALUES('test@gmail.com','12345','tom kru','USER');

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

--
    INSERT INTO books(id_series,title,subtitle,description,cover,book_in_series_no)
    VALUES(3,'Odyssey One', 'Ostatni bastion','Trzeci tom pasjonującego cyklu SF Evana Curie.','adresURL', 3);
      INSERT INTO books(id_series,title,subtitle,description,cover,book_in_series_no)
    VALUES(3,'Odyssey One', 'W ogniu wojny','Trzeci tom pasjonującego cyklu SF Evana Curie.','adresURL_inny', 4);
      INSERT INTO books(id_series,title,subtitle,description,cover,book_in_series_no)
    VALUES(3,'Odyssey One', 'Król Wojowników','Trzeci tom pasjonującego cyklu SF Evana Curie.','adresURL_jesczczeInny', 5);
