package ru.swdmi.booklibrary.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.repository.AuthorRepositoryJpa;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис для работы с авторами")
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepositoryJpa authorRepository;

    @DisplayName("должен возвращать всех авторов")
    @Test
    void findAll() {
        Author expectedAuthor = new Author(100L, "Ivan");
        given(authorRepository.findAll()).willReturn(List.of(expectedAuthor));

        assertThat(authorService.findAll()).containsExactly(expectedAuthor);
    }
}