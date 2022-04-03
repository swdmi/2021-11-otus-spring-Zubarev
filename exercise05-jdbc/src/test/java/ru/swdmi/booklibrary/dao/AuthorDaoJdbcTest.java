package ru.swdmi.booklibrary.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.swdmi.booklibrary.dao.mapper.AuthorMapper;
import ru.swdmi.booklibrary.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import({AuthorDaoJdbc.class, AuthorMapper.class})
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDao;

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
        List<Author> actualAuthors = authorDao.findAll();
        assertThat(actualAuthors).containsExactlyInAnyOrder(expectedAuthor, anotherExpectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по его имени")
    @Test
    void shouldReturnAuthorByName() {
        List<Author> actualAuthors = authorDao.findByName(expectedAuthor.getName());
        assertThat(actualAuthors.size()).isEqualTo(1);
        assertThat(actualAuthors).containsOnly(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по его идентификатору")
    @Test
    void shouldReturnAuthorById() {
        Author actualAuthors = authorDao.findById(expectedAuthor.getId()).orElseThrow();
        assertThat(actualAuthors).isEqualTo(expectedAuthor);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author newExpectedAuthor = new Author("Lev");
        authorDao.insert(newExpectedAuthor);
        Author actualAuthor = authorDao.findByName(newExpectedAuthor.getName()).stream().findFirst().orElseThrow();
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(newExpectedAuthor);
    }
}
