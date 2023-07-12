insert into giocatore values(nextval('giocatore_seq'), 'Melchiorri', '10/02/2001', 'Giorgio', 31, 'Ala grande');
insert into giocatore values(nextval('giocatore_seq'), 'Proietti', '10/02/2001', 'Gianluca', 13, 'Centro');
insert into giocatore values(nextval('giocatore_seq'), 'Gai', '10/02/2001', 'Daniele', 3, 'Guardia tiratrice');
insert into giocatore values(nextval('giocatore_seq'), 'Gramegna', '10/02/2001', 'Giulio', 11, 'Playmaker');
insert into giocatore values(nextval('giocatore_seq'), 'Melchiorri', '10/05/1998', 'Matteo', 9, 'Ala piccola');

insert into partita values(nextval('partita_seq'), '05/11/2023', 'Colleferro', 68, 63);



insert into users (id, name, surname, email)  values(nextval('hibernate_sequence'), 'Luca', 'Luca' ,'luca@luca.it');
insert into credentials (id, username, password, role,user_id) values(nextval('hibernate_sequence'), 'Luca', '$2a$10$H2HR949PLG9rWBwE2iMNmOgD9tJvRSsXjI2eMyDdu0iSOMgXXjsdy' ,'ADMIN',nextval('hibernate_sequence') -2);
insert into users (id, name, surname, email)  values(nextval('hibernate_sequence'), 'Paolo', 'Paolo' ,'paolo@paolo.it');
insert into credentials (id, username, password, role,user_id) values(nextval('hibernate_sequence'), 'User', '$2a$10$H2HR949PLG9rWBwE2iMNmOgD9tJvRSsXjI2eMyDdu0iSOMgXXjsdy' ,'DEFAULT',nextval('hibernate_sequence') -2);