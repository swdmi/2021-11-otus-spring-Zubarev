package ru.swdmi.booklibrary.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должно")
@DataJpaTest
class BookRepositoryJpaTest {
    private final static long EXISTED_AUTHOR_ID = 100L;
    private final static long EXISTED_GENRE_ID = 100L;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать книгу по её идентификатору")
    @Test
    void shouldReturnById() {
        Author existedAuthor = em.find(Author.class, EXISTED_AUTHOR_ID);
        Genre existedGenre = em.find(Genre.class, EXISTED_GENRE_ID);

        Book expectedBook = new Book("Book title", existedAuthor, existedGenre);
        em.persist(expectedBook);

        em.flush();
        em.clear();

        Optional<Book> actualBook = bookRepository.findById(expectedBook.getId()).stream().findFirst();
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }
}