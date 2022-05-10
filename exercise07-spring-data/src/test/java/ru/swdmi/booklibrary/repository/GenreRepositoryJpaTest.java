package ru.swdmi.booklibrary.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.swdmi.booklibrary.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с жанрами должно")
@DataJpaTest
class GenreRepositoryJpaTest {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать жанр по его имени")
    @Test
    void shouldReturnById() {
        Genre expectedGenre = new Genre("Comedy");
        em.persist(expectedGenre);

        em.flush();
        em.clear();

        Optional<Genre> actualGenre = genreRepository.findByName(expectedGenre.getName()).stream().findFirst();
        assertThat(actualGenre).isPresent().get().usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}