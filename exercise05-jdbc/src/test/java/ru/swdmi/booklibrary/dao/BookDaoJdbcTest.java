package ru.swdmi.booklibrary.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.swdmi.booklibrary.dao.mapper.BookMapper;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({BookDaoJdbc.class, BookMapper.class})
class BookDaoJdbcTest {
    @Autowired
    private BookDaoJdbc bookDao;

    private static Author author;
    private static Author anotherAuthor;
    private static Genre genre;
    private static Genre anotherGenre;

    private Book expectedBook;
    private Book anotherExpectedBook;

    @BeforeAll
    static void setUpClass() {
        author = new Author(100L, "Ivan");
        anotherAuthor = new Author(101L, "John");
        genre = new Genre(100L, "Comedy");
        anotherGenre = new Genre(101L, "Crime");
    }

    @BeforeEach
    void setUp() {
        expectedBook = Book.builder()
                .id(100L)
                .title("Book1")
                .author(author)
                .genre(genre)
                .build();

        anotherExpectedBook = Book.builder()
                .id(101L)
                .title("Book2")
                .author(anotherAuthor)
                .genre(anotherGenre)
                .build();
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book newBook = Book.builder()
                .title("New book")
                .author(author)
                .genre(genre)
                .build();
        bookDao.insert(newBook);

        assertThat(newBook.getId()).isNotNull();
        Book actualBook = bookDao.findById(newBook.getId()).orElseThrow();

        assertThat(actualBook.getTitle()).isEqualTo(newBook.getTitle());
        assertThat(actualBook.getAuthor()).isEqualTo(newBook.getAuthor());
        assertThat(actualBook.getGenre()).isEqualTo(newBook.getGenre());
    }

    @DisplayName("возвращать ожидаемую книгу по её идентификатору")
    @Test
    void shouldReturnExpectedBookById() {
        Book actualBook = bookDao.findById(expectedBook.getId()).orElseThrow();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("изменять информацию о книге в БД")
    @Test
    void shouldUpdateBook() {
        expectedBook.setTitle("New Title");
        expectedBook.setAuthor(anotherAuthor);
        expectedBook.setGenre(anotherGenre);
        bookDao.update(expectedBook);
        Book actualBook = bookDao.findById(expectedBook.getId()).orElseThrow();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу по её идентификатору")
    @Test
    void shouldDeleteBookById() {
        bookDao.delete(expectedBook.getId());
        Optional<Book> actualBook = bookDao.findById(expectedBook.getId());
        assertThat(actualBook).isEmpty();
    }

    @DisplayName("возвращать все книги из БД")
    @Test
    void shouldReturnExpectedBooksList() {
        List<Book> actualBooks = bookDao.findAll();
        assertThat(actualBooks).containsExactly(expectedBook, anotherExpectedBook);
    }
}