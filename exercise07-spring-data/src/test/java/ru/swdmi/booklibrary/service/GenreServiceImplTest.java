package ru.swdmi.booklibrary.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.repository.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис для работы с жанрами")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {
    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreRepository genreRepository;

    @DisplayName("должен возвращать все жанры")
    @Test
    void findAll() {
        Genre expectedGenre = new Genre(100L, "Comedy");
        given(genreRepository.findAll()).willReturn(List.of(expectedGenre));

        assertThat(genreService.findAll()).containsExactly(expectedGenre);
    }
}