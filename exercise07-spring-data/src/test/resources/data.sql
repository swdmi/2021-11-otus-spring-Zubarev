insert into authors(id, name) values (100, 'Ivan');
insert into genres(id, name) values (100, 'Comedy');
insert into books(id, title, author_id, genre_id) values (100, 'Book1',
           select max (id) from authors where name = 'Ivan ',
           select max (id) from genres where name = 'Comedy');