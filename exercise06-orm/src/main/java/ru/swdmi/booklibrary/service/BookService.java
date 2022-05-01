package ru.swdmi.booklibrary.service;

import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.service.model.BookSaveRequest;
import ru.swdmi.booklibrary.service.model.BookWithIdsSaveRequest;

import java.util.List;

public interface BookService {
    Book save(BookWithIdsSaveRequest bookSaveRequest);
    Book save(BookSaveRequest bookSaveRequest);
    List<Book> findAll();
    Book findById(long id);
    void deleteById(long id);
}
