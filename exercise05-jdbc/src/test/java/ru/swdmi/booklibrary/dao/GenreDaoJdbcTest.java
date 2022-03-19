package ru.swdmi.booklibrary.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.swdmi.booklibrary.dao.mapper.GenreMapper;
import ru.swdmi.booklibrary.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import({GenreDaoJdbc.class, GenreMapper.class})
class GenreDaoJdbcTest {
    @Autowired
    private GenreDaoJdbc genreDao;

    private static Genre expectedGenre;
    private static Genre anotherExpectedGenre;

    @BeforeAll
    static void setUpClass() {
        expectedGenre = new Genre(100L, "Comedy");
        anotherExpectedGenre = new Genre(101L, "Crime");
    }

    @DisplayName("возвращать все жанры")
    @Test
    void shouldReturnExpectedGenresList() {
        List<Genre> actualAuthors = genreDao.findAll();
        assertThat(actualAuthors).containsExactlyInAnyOrder(expectedGenre, anotherExpectedGenre);

    }

    @DisplayName("возвращать ожидаемый жанр по его имени")
    @Test
    void shouldReturnGenreByName() {
        List<Genre> actualAuthors = genreDao.findByName(expectedGenre.getName());
        assertThat(actualAuthors.size()).isEqualTo(1);
        assertThat(actualAuthors).containsOnly(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр по его идентификатору")
    @Test
    void shouldReturnGenreById() {
        Genre actualAuthors = genreDao.findById(expectedGenre.getId()).orElseThrow();
        assertThat(actualAuthors).isEqualTo(expectedGenre);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertGenre() {
        Genre newExpectedGenre = new Genre("Horror");
        genreDao.insert(newExpectedGenre);
        Genre actualGenre = genreDao.findByName(newExpectedGenre.getName()).stream().findFirst().orElseThrow();
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(newExpectedGenre);
    }
}