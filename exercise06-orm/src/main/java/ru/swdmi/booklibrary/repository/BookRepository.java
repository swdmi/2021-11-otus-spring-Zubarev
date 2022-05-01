package ru.swdmi.booklibrary.repository;

import ru.swdmi.booklibrary.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(long id);
    Optional<Book> findByIdWithLazyAll(long id);
    Optional<Book> findByIdWithLazyComments(long id);
    List<Book> findByName(String bookTitle);
    Book save(Book book);
    void delete(long id);
    List<Book> findAll();

}
