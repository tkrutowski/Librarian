   INSERT INTO users(login,password,name,is_admin) VALUES('admin','1234','administrator',1);
    INSERT INTO users(login,password,name,is_admin) VALUES('test@gmail.com','12345','tom kru',0);

      INSERT INTO categories(name) VALUES('SF');
        INSERT INTO categories(name) VALUES('Fantasy');


          INSERT INTO authors(first_name, last_name) VALUES('Lee','Child' );
    INSERT INTO authors(first_name, last_name) VALUES('Jo','Nesbo' );
    INSERT INTO authors(first_name, last_name) VALUES('Tom','Clancy' );
    INSERT INTO authors(first_name, last_name) VALUES('Ryk','Brown' );
    INSERT INTO authors(first_name, last_name) VALUES('Remigiusz','Mróz' );


       INSERT INTO series(title, description) VALUES('Jack Reacher','dsfsdfs dfsfds ds sd s' );
       INSERT INTO series(title, description) VALUES('The Frontires Saga','d55435 grtet sfsdfs dfsfds ds sd s' );

       INSERT INTO bookstores(name, www) VALUES('Empik','www.empik.com' );
       INSERT INTO bookstores(name, www) VALUES('PWN','www.pwn.pl' );

--
--     INSERT INTO books(id_user,id_bookstore,id_series,title,subtitle,description,cover,edition_type,reading_status,ownership_status,read_from,read_to,info,is_read,isbn,volume)
--     VALUES(1,1,1,'Burza',null,'brak opisu',NULL,'EBOOK','NOT_READ','READ_ONLY',null,null,'info1',0,'789-987',1);
--     INSERT INTO books(id_user,id_bookstore,id_series,title,subtitle,description,cover,edition_type,reading_status,ownership_status,is_read)
--     VALUES(2,0,1,'Sztorm',null,'brak opisu',NULL,'AUDIOBOOK','READ','READ_ONLY',null,null,'info2',0);

