insert into authors(name) values ('Lev Tolstoy');
insert into authors(name) values ('Stephen King');
insert into genres(name) values ('Novel');
insert into genres(name) values ('Horror');
insert into books(title, author_id, genre_id) values ('War and Peace',
                                                     select max(id) from authors where name = 'Lev Tolstoy',
                                                     select max(id) from genres where name = 'Novel');
insert into books(title, author_id, genre_id) values ('Anna Karenina',
                                                     select max(id) from authors where name = 'Lev Tolstoy',
                                                     select max(id) from genres where name = 'Novel');
insert into books(title, author_id, genre_id) values ('The Shining',
                                                     select max(id) from authors where name = 'Stephen King',
                                                     select max(id) from genres where name = 'Horror');
insert into books(title, author_id, genre_id) values ('Cujo',
                                                     select max(id) from authors where name = 'Stephen King',
                                                     select max(id) from genres where name = 'Horror');

insert into comments(text, book_id) values ('Cool!!', select max(id) from books where title = 'War and Peace');
insert into comments(text, book_id) values ('Too long...', select max(id) from books where title = 'War and Peace');
insert into comments(text, book_id) values ('Something interesting', select max(id) from books where title = 'War and Peace');
insert into comments(text, book_id) values ('I like it!', select max(id) from books where title = 'War and Peace');
