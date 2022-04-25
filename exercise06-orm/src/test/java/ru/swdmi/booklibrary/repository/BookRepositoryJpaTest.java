package ru.swdmi.booklibrary.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Genre;

import javax.persistence.PersistenceUnitUtil;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должно")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {
    @Autowired
    private BookRepositoryJpa bookRepository;
    @Autowired
    private TestEntityManager em;

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
        expectedBook = new Book(100L, "Book1", author, genre);

        anotherExpectedBook = new Book(101L, "Book2", anotherAuthor, anotherGenre);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book newBook = new Book("New book",
                em.find(Author.class, author.getId()),
                em.find(Genre.class, genre.getId()));
        bookRepository.save(newBook);

        em.flush();
        em.clear();

        assertThat(newBook.getId()).isNotNull();
        Book actualBook = bookRepository.findById(newBook.getId()).orElseThrow();

        assertThat(actualBook.getTitle()).isEqualTo(newBook.getTitle());
        assertThat(actualBook.getAuthor()).isEqualTo(newBook.getAuthor());
        assertThat(actualBook.getGenre()).isEqualTo(newBook.getGenre());
    }

    @DisplayName("возвращать ожидаемую книгу по её идентификатору")
    @Test
    void shouldReturnExpectedBookById() {
        Book actualBook = bookRepository.getOne(expectedBook.getId()).orElseThrow();
        Book expectedBookFromDb = em.find(Book.class, expectedBook.getId());

        em.clear();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBookFromDb);
    }

    @DisplayName("возвращать полную информацию об ожидаемой книге по её идентификатору")
    @Test
    void shouldReturnExpectedBookFullInfoById() {
        Book actualBook = bookRepository.findById(expectedBook.getId()).orElseThrow();
        Book expectedBookFromDb = em.find(Book.class, expectedBook.getId());

        em.clear();
        PersistenceUnitUtil unitUtil = em.getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil();

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBookFromDb);
        assertThat(unitUtil.isLoaded(actualBook.getComments())).isTrue();
    }

    @DisplayName("изменять информацию о книге в БД")
    @Test
    void shouldUpdateBook() {
        expectedBook.setTitle("New Title");
        expectedBook.setAuthor(anotherAuthor);
        expectedBook.setGenre(anotherGenre);
        bookRepository.save(expectedBook);

        em.flush();
        em.clear();

        Book actualBook = bookRepository.findById(expectedBook.getId()).orElseThrow();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу по её идентификатору")
    @Test
    void shouldDeleteBookById() {
        bookRepository.delete(expectedBook.getId());

        em.flush();
        em.clear();

        Optional<Book> actualBook = Optional.ofNullable(em.find(Book.class, expectedBook.getId()));
        assertThat(actualBook).isEmpty();
    }

    @DisplayName("возвращать все книги из БД")
    @Test
    void shouldReturnExpectedBooksList() {
        List<Book> actualBooks = bookRepository.findAll();
        assertThat(actualBooks).containsExactly(expectedBook, anotherExpectedBook);
    }
}