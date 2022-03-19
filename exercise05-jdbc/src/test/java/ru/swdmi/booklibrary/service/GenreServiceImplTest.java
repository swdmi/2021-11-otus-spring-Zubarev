package ru.swdmi.booklibrary.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.swdmi.booklibrary.dao.GenreDaoJdbc;
import ru.swdmi.booklibrary.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис для работы с жанрами")
@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {
    @InjectMocks
    private GenreServiceImpl genreService;

    @Mock
    private GenreDaoJdbc genreDao;

    @DisplayName("должен возвращать все жанры")
    @Test
    void findAll() {
        Genre expectedGenre = new Genre(100L, "Comedy");
        given(genreDao.findAll()).willReturn(List.of(expectedGenre));

        assertThat(genreService.findAll()).containsExactly(expectedGenre);
    }
}