insert into movie (id, title, year) values(nextval('hibernate_sequence'), 'Full metal jacket', 1987);
insert into movie (id, title, year) values(nextval('hibernate_sequence'), 'Non e'' un paese per vecchi',2007);
insert into movie (id, title, year) values(nextval('hibernate_sequence'), 'The founder',2016);
insert into movie (id, title, year) values(nextval('hibernate_sequence'), 'Harry Potter e la pietra filosofale',2001);
insert into movie (id, title, year) values(nextval('hibernate_sequence'), 'Il pianeta delle scimmie',2001);
insert into movie (id, title, year) values(nextval('hibernate_sequence'), 'Lo chiamavano Jeeg Robot',2015);
insert into movie (id, title, year,immagini) values(nextval('hibernate_sequence'), 'Yesterday',2019,'yesterday.jpeg;Dua Lipa.jpeg');

insert into artist (id, name, surname, date_of_birth) values(nextval('hibernate_sequence'), 'Tim', 'Burton','1958-08-25');
insert into artist (id, name, surname, date_of_birth) values(nextval('hibernate_sequence'), 'Himesh', 'Patel','1990-10-13');
insert into artist (id, name, surname, date_of_birth) values(nextval('hibernate_sequence'), 'Danny', 'Boyle','1956-10-20');
insert into artist (id, name, surname, date_of_birth) values(nextval('hibernate_sequence'), 'Tim', 'Roth','1961-05-14');
insert into artist (id, name, surname, date_of_birth) values(nextval('hibernate_sequence'), 'Michael', 'Keaton','1951-09-05');
insert into artist (id, name, surname, date_of_birth,date_of_death) values(nextval('hibernate_sequence'), 'Stanley', 'Kubrick','1928-07-26','1999-03-07');
insert into artist (id, name, surname, date_of_birth) values(nextval('hibernate_sequence'), 'Ronald Lee', 'Ermey','1944-03-24');	
insert into artist (id, name, surname, date_of_birth) values(nextval('hibernate_sequence'), 'Claudio', 'Santamaria','1974-07-22');
insert into artist (id, name, surname, date_of_birth) values(nextval('hibernate_sequence'), 'Gabriele', 'Mainetti','1976-11-07');








insert into users (id, name, surname, email)  values(nextval('hibernate_sequence'), 'Luca', 'Luca' ,'luca@luca.it');
insert into credentials (id, username, password, role,user_id) values(nextval('hibernate_sequence'), 'Luca', '$2a$10$H2HR949PLG9rWBwE2iMNmOgD9tJvRSsXjI2eMyDdu0iSOMgXXjsdy' ,'ADMIN',nextval('hibernate_sequence') -2);
insert into users (id, name, surname, email)  values(nextval('hibernate_sequence'), 'Paolo', 'Paolo' ,'paolo@paolo.it');
insert into credentials (id, username, password, role,user_id) values(nextval('hibernate_sequence'), 'User', '$2a$10$H2HR949PLG9rWBwE2iMNmOgD9tJvRSsXjI2eMyDdu0iSOMgXXjsdy' ,'DEFAULT',nextval('hibernate_sequence') -2);


