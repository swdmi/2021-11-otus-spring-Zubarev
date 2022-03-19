package ru.swdmi.booklibrary.service;

import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.dto.BookDTO;
import ru.swdmi.booklibrary.domain.dto.BookWithIdsDTO;

import java.util.List;

public interface BookService {
    Book save(BookWithIdsDTO bookDTO);
    Book save(BookDTO bookDTO);
    List<Book> findAll();
    Book findById(long id);
    void deleteById(long id);
    Book update(BookWithIdsDTO bookDTO);
    Book update(BookDTO bookDTO);
}
