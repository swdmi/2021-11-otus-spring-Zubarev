package ru.swdmi.booklibrary.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.swdmi.booklibrary.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с авторами должен")
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;


    @DisplayName("возвращать автора по его имени")
    @Test
    void shouldReturnById() {
        Author expectedAuthor = new Author("Ivan");
        em.persist(expectedAuthor);

        em.flush();
        em.clear();

        Optional<Author> actualAuthor = authorRepository.findByName(expectedAuthor.getName()).stream().findFirst();
        assertThat(actualAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}
