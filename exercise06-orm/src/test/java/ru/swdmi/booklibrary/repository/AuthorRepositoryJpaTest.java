package ru.swdmi.booklibrary.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.swdmi.booklibrary.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с авторами должно")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepositoryJpa authorRepository;

    @Autowired
    private TestEntityManager em;

    private Author expectedAuthor;
    private Author anotherExpectedAuthor;

    @BeforeEach
    void setUpClass() {
        expectedAuthor = new Author(100L, "Ivan");
        anotherExpectedAuthor = new Author(101L, "John");
    }

    @DisplayName("возвращать всех авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        List<Author> actualAuthors = authorRepository.findAll();
        assertThat(actualAuthors).containsExactlyInAnyOrder(expectedAuthor, anotherExpectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по его имени")
    @Test
    void shouldReturnAuthorByName() {
        List<Author> actualAuthors = authorRepository.findByName(expectedAuthor.getName());
        assertThat(actualAuthors.size()).isEqualTo(1);
        assertThat(actualAuthors).containsOnly(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по его идентификатору")
    @Test
    void shouldReturnAuthorById() {
        Author actualAuthors = authorRepository.findById(expectedAuthor.getId()).orElseThrow();
        Author expectedAuthorFromDb = em.find(Author.class, expectedAuthor.getId());
        assertThat(actualAuthors).isEqualTo(expectedAuthorFromDb);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author newExpectedAuthor = new Author("Lev");
        authorRepository.save(newExpectedAuthor);
        em.clear();

        Author actualAuthor = em.find(Author.class, newExpectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(newExpectedAuthor);
    }
}
