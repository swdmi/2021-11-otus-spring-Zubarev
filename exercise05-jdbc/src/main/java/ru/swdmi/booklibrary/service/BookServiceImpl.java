package ru.swdmi.booklibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.swdmi.booklibrary.dao.AuthorDao;
import ru.swdmi.booklibrary.dao.BookDao;
import ru.swdmi.booklibrary.dao.GenreDao;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.domain.dto.BookDTO;
import ru.swdmi.booklibrary.domain.dto.BookWithIdsDTO;
import ru.swdmi.booklibrary.exception.LibraryException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    @Transactional
    public Book save(BookWithIdsDTO bookDTO) {
        Author author = authorDao.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new LibraryException("Can't find author by id = %s", bookDTO.getAuthorId()));
        Genre genre = genreDao.findById(bookDTO.getGenreId())
                .orElseThrow(() -> new LibraryException("Can't find genre by id = %s", bookDTO.getGenreId()));
        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .author(author)
                .genre(genre)
                .build();
        return bookDao.insert(book);
    }

    @Override
    @Transactional
    public Book save(BookDTO bookDTO) {
        Author author = authorDao.findByName(bookDTO.getAuthor()).stream()
                .findFirst()
                .orElse(authorDao.insert(new Author(bookDTO.getAuthor())));
        Genre genre = genreDao.findByName(bookDTO.getGenre()).stream()
                .findFirst()
                .orElse(genreDao.insert(new Genre(bookDTO.getGenre())));
        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .author(author)
                .genre(genre)
                .build();
        return bookDao.insert(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        return bookDao.findById(id).orElseThrow(() -> new LibraryException("Can't find book by id = %s", id));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookDao.delete(id);
    }

    @Override
    @Transactional
    public Book update(BookWithIdsDTO bookDTO) {
        Author author = authorDao.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new LibraryException("Can't find author by id = %s", bookDTO.getAuthorId()));
        Genre genre = genreDao.findById(bookDTO.getGenreId())
                .orElseThrow(() -> new LibraryException("Can't find genre by id = %s", bookDTO.getGenreId()));
        Book book = Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(author)
                .genre(genre)
                .build();
        return bookDao.update(book);
    }

    @Override
    @Transactional
    public Book update(BookDTO bookDTO) {
        Author author = authorDao.findByName(bookDTO.getAuthor()).stream()
                .findFirst()
                .orElse(authorDao.insert(new Author(bookDTO.getAuthor())));
        Genre genre = genreDao.findByName(bookDTO.getGenre()).stream()
                .findFirst()
                .orElse(genreDao.insert(new Genre(bookDTO.getGenre())));
        Book book = Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(author)
                .genre(genre)
                .build();
        return bookDao.update(book);
    }
}
