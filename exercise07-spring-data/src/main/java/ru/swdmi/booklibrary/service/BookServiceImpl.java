package ru.swdmi.booklibrary.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.exception.LibraryException;
import ru.swdmi.booklibrary.repository.AuthorRepository;
import ru.swdmi.booklibrary.repository.BookRepository;
import ru.swdmi.booklibrary.repository.GenreRepository;
import ru.swdmi.booklibrary.service.model.AbstractBookSaveRequest;
import ru.swdmi.booklibrary.service.model.BookSaveRequest;
import ru.swdmi.booklibrary.service.model.BookWithIdsSaveRequest;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public Book save(BookWithIdsSaveRequest bookSaveRequest) {
        Author author = authorRepository.findById(bookSaveRequest.getAuthorId())
                .orElseThrow(() -> new LibraryException("Can't find author by id = %s", bookSaveRequest.getAuthorId()));
        Genre genre = genreRepository.findById(bookSaveRequest.getGenreId())
                .orElseThrow(() -> new LibraryException("Can't find genre by id = %s", bookSaveRequest.getGenreId()));
        return innerSave(bookSaveRequest, author, genre);
    }

    @Override
    @Transactional
    public Book save(BookSaveRequest bookSaveRequest) {
        Author author = authorRepository.findByName(bookSaveRequest.getAuthor()).stream()
                .findFirst()
                .orElse(authorRepository.save(new Author(bookSaveRequest.getAuthor())));
        Genre genre = genreRepository.findByName(bookSaveRequest.getGenre()).stream()
                .findFirst()
                .orElse(genreRepository.save(new Genre(bookSaveRequest.getGenre())));
        return innerSave(bookSaveRequest, author, genre);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new LibraryException("Can't find book by id = %s", id));
        Hibernate.initialize(book.getComments());
        return book;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Book book = bookRepository.getById(id);
        bookRepository.delete(book);
    }

    private Book innerSave(AbstractBookSaveRequest bookSaveRequest, Author author, Genre genre) {
        if (bookSaveRequest.isCreatable()) {
            Book book = new Book(bookSaveRequest.getTitle(), author, genre);
            return bookRepository.save(book);
        } else {
            Book bookInDb = bookRepository.getById(bookSaveRequest.getId());
            bookInDb.setAuthor(author);
            bookInDb.setGenre(genre);
            bookInDb.setTitle(bookSaveRequest.getTitle());
            return bookInDb;
        }
    }
}
