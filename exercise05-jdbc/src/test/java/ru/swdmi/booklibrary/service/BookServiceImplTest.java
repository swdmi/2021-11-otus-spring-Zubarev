package ru.swdmi.booklibrary.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.swdmi.booklibrary.dao.AuthorDaoJdbc;
import ru.swdmi.booklibrary.dao.BookDaoJdbc;
import ru.swdmi.booklibrary.dao.GenreDaoJdbc;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.domain.dto.BookDTO;
import ru.swdmi.booklibrary.domain.dto.BookWithIdsDTO;
import ru.swdmi.booklibrary.exception.LibraryException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для работы с книгами")
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookDaoJdbc bookDao;
    @Mock
    private AuthorDaoJdbc authorDao;
    @Mock
    private GenreDaoJdbc genreDao;

    private static Author existingAuthor;
    private static Genre existingGenre;
    private static Author newAuthor;
    private static Genre newGenre;

    @BeforeAll
    static void setUpClass() {
        existingAuthor = new Author(100L, "Ivan");
        existingGenre = new Genre(100L, "Comedy");
        newAuthor = new Author("Anybody");
        newGenre = new Genre("Somewhat");
    }

    @DisplayName("должен сохранять книгу с указанием идентификаторов автора и жанра")
    @Test
    void shouldSaveFromBookWithIdsDTO() {
        BookWithIdsDTO bookWithIdsDTO = new BookWithIdsDTO("Title", existingAuthor.getId(), existingGenre.getId());

        given(authorDao.findById(bookWithIdsDTO.getAuthorId()))
                .willReturn(Optional.of(existingAuthor));
        given(genreDao.findById(bookWithIdsDTO.getGenreId()))
                .willReturn(Optional.of(existingGenre));
        given(bookDao.insert(any()))
                .will(invocation -> invocation.getArgument(0));
        Book actualBook = bookService.save(bookWithIdsDTO);
        assertThat(actualBook.getTitle()).isEqualTo(bookWithIdsDTO.getTitle());
        assertThat(actualBook.getAuthor().getId()).isEqualTo(bookWithIdsDTO.getAuthorId());
        assertThat(actualBook.getGenre().getId()).isEqualTo(bookWithIdsDTO.getGenreId());
    }

    @DisplayName("должен сохранять книгу с указанием имен автора и жанра")
    @Test
    void shouldSaveFromBookDTO() {
        BookDTO bookDTO = new BookDTO("Title", newAuthor.getName(), newGenre.getName());

        given(authorDao.findByName(bookDTO.getAuthor()))
                .willReturn(Collections.emptyList());
        given(genreDao.findByName(bookDTO.getGenre()))
                .willReturn(Collections.emptyList());
        given(authorDao.insert(any()))
                .will(invocation -> invocation.getArgument(0));
        given(genreDao.insert(any()))
                .will(invocation -> invocation.getArgument(0));
        given(bookDao.insert(any()))
                .will(invocation -> invocation.getArgument(0));

        Book actualBook = bookService.save(bookDTO);
        assertThat(actualBook.getTitle()).isEqualTo(bookDTO.getTitle());
        assertThat(actualBook.getAuthor().getName()).isEqualTo(bookDTO.getAuthor());
        assertThat(actualBook.getGenre().getName()).isEqualTo(bookDTO.getGenre());
    }

    @DisplayName("должен возвращать все книги из БД")
    @Test
    void shouldReturnAllBook() {
        Book expectedBook = Book.builder()
                .id(100L)
                .title("Book1")
                .author(existingAuthor)
                .genre(existingGenre)
                .build();

        given(bookDao.findAll()).willReturn(List.of(expectedBook));
        assertThat(bookService.findAll()).containsExactly(expectedBook);
    }

    @DisplayName("должен возвращать существующую книгу по её идентификатору")
    @Test
    void shouldReturnExistingBookById() {
        Book expectedBook = Book.builder()
                .id(100L)
                .title("Book1")
                .author(existingAuthor)
                .genre(existingGenre)
                .build();

        long bookId = expectedBook.getId();
        given(bookDao.findById(bookId)).willReturn(Optional.of(expectedBook));
        assertThat(bookService.findById(bookId)).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("должен выбрасывать исключение при поиске несуществующей книги по её идентификатору")
    @Test
    void shouldThrowExceptionOnNonExistingBookById() {
        long bookId = 0L;
        given(bookDao.findById(bookId)).willReturn(Optional.empty());
        assertThatCode(() -> bookService.findById(bookId)).isInstanceOf(LibraryException.class);
    }

    @DisplayName("должен удалять книгу по её идентификатору")
    @Test
    void shouldDeleteBookById() {
        long bookId = 1L;
        bookService.deleteById(bookId);
        verify(bookDao).delete(bookId);
    }

    @DisplayName("должен обновлять книгу новыми значениями, в т.ч. идентификаторами автора и жанра")
    @Test
    void shouldUpdateWithIds() {
        BookWithIdsDTO bookWithIdsDTO = new BookWithIdsDTO(100L, "Title", existingAuthor.getId(), existingGenre.getId());

        given(authorDao.findById(bookWithIdsDTO.getAuthorId()))
                .willReturn(Optional.of(existingAuthor));
        given(genreDao.findById(bookWithIdsDTO.getGenreId()))
                .willReturn(Optional.of(existingGenre));
        given(bookDao.update(any()))
                .will(invocation -> invocation.getArgument(0));

        Book actualBook = bookService.update(bookWithIdsDTO);
        assertThat(actualBook.getTitle()).isEqualTo(bookWithIdsDTO.getTitle());
        assertThat(actualBook.getAuthor().getId()).isEqualTo(bookWithIdsDTO.getAuthorId());
        assertThat(actualBook.getGenre().getId()).isEqualTo(bookWithIdsDTO.getGenreId());
    }


    @DisplayName("должен обновлять книгу новыми значениями, в т.ч. именами автора и жанра")
    @Test
    void shouldUpdateWithNames() {
        BookDTO bookDTO = new BookDTO("Title", newAuthor.getName(), newGenre.getName());

        given(authorDao.findByName(bookDTO.getAuthor()))
                .willReturn(Collections.emptyList());
        given(genreDao.findByName(bookDTO.getGenre()))
                .willReturn(Collections.emptyList());
        given(authorDao.insert(any()))
                .will(invocation -> invocation.getArgument(0));
        given(genreDao.insert(any()))
                .will(invocation -> invocation.getArgument(0));
        given(bookDao.update(any()))
                .will(invocation -> invocation.getArgument(0));

        Book actualBook = bookService.update(bookDTO);
        assertThat(actualBook.getTitle()).isEqualTo(bookDTO.getTitle());
        assertThat(actualBook.getAuthor().getName()).isEqualTo(bookDTO.getAuthor());
        assertThat(actualBook.getGenre().getName()).isEqualTo(bookDTO.getGenre());
    }

}