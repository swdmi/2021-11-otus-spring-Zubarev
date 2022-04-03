package ru.swdmi.booklibrary.dao;

import ru.swdmi.booklibrary.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book insert(Book book);
    Optional<Book> findById(long id);
    Book update(Book book);
    void delete(long id);
    List<Book> findAll();

}
