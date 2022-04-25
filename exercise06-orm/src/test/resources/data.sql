insert into authors(id, name) values (100, 'Ivan');
insert into authors(id, name) values (101, 'John');
insert into genres(id, name) values (100, 'Comedy');
insert into genres(id, name) values (101, 'Crime');
insert into books(id, title, author_id, genre_id) values (100, 'Book1',
                                                     select max(id) from authors where name = 'Ivan',
                                                     select max(id) from genres where name = 'Comedy');
insert into books(id, title, author_id, genre_id) values (101, 'Book2',
                                                     select max(id) from authors where name = 'John',
                                                     select max(id) from genres where name = 'Crime');
insert into comments(id, text, book_id) values (100, 'Comment1 for Book1', select max(id) from books where title = 'Book1');
insert into comments(id, text, book_id) values (101, 'Comment2 for Book1', select max(id) from books where title = 'Book1');
insert into comments(id, text, book_id) values (102, 'Comment for Book2', select max(id) from books where title = 'Book2');
