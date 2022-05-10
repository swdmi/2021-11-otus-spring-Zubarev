package ru.swdmi.booklibrary.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.exception.LibraryException;
import ru.swdmi.booklibrary.repository.AuthorRepository;
import ru.swdmi.booklibrary.repository.BookRepository;
import ru.swdmi.booklibrary.repository.GenreRepository;
import ru.swdmi.booklibrary.service.model.BookSaveRequest;
import ru.swdmi.booklibrary.service.model.BookWithIdsSaveRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для работы с книгами должен ")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;

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

    @DisplayName("сохранять книгу с указанием идентификаторов автора и жанра")
    @Test
    void shouldSaveNewBookWithIdsDTO() {
        BookWithIdsSaveRequest bookWithIdsSaveRequest = new BookWithIdsSaveRequest("Title", existingAuthor.getId(), existingGenre.getId());

        given(authorRepository.findById(bookWithIdsSaveRequest.getAuthorId()))
                .willReturn(Optional.of(existingAuthor));
        given(genreRepository.findById(bookWithIdsSaveRequest.getGenreId()))
                .willReturn(Optional.of(existingGenre));
        given(bookRepository.save(any()))
                .will(invocation -> invocation.getArgument(0));
        Book actualBook = bookService.save(bookWithIdsSaveRequest);
        assertThat(actualBook.getTitle()).isEqualTo(bookWithIdsSaveRequest.getTitle());
        assertThat(actualBook.getAuthor().getId()).isEqualTo(bookWithIdsSaveRequest.getAuthorId());
        assertThat(actualBook.getGenre().getId()).isEqualTo(bookWithIdsSaveRequest.getGenreId());
    }

    @DisplayName("сохранять книгу с указанием имен автора и жанра")
    @Test
    void shouldSaveNewBookWithNames() {
        BookSaveRequest bookSaveRequest = new BookSaveRequest("Title", newAuthor.getName(), newGenre.getName());

        given(authorRepository.findByName(bookSaveRequest.getAuthor()))
                .willReturn(Collections.emptyList());
        given(genreRepository.findByName(bookSaveRequest.getGenre()))
                .willReturn(Collections.emptyList());
        given(authorRepository.save(any()))
                .will(invocation -> invocation.getArgument(0));
        given(genreRepository.save(any()))
                .will(invocation -> invocation.getArgument(0));
        given(bookRepository.save(any()))
                .will(invocation -> invocation.getArgument(0));

        Book actualBook = bookService.save(bookSaveRequest);
        assertThat(actualBook.getTitle()).isEqualTo(bookSaveRequest.getTitle());
        assertThat(actualBook.getAuthor().getName()).isEqualTo(bookSaveRequest.getAuthor());
        assertThat(actualBook.getGenre().getName()).isEqualTo(bookSaveRequest.getGenre());
    }

    @DisplayName("возвращать все книги из БД")
    @Test
    void shouldReturnAllBook() {
        Book expectedBook = new Book(100L, "Book1", existingAuthor, existingGenre);
        given(bookRepository.findAll()).willReturn(List.of(expectedBook));
        assertThat(bookService.findAll()).containsExactly(expectedBook);
    }

    @DisplayName("возвращать существующую книгу по её идентификатору")
    @Test
    void shouldReturnExistingBookById() {
        Book expectedBook = new Book(100L, "Book1", existingAuthor, existingGenre);

        long bookId = expectedBook.getId();
        given(bookRepository.findById(bookId)).willReturn(Optional.of(expectedBook));
        assertThat(bookService.findById(bookId)).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("выбрасывать исключение при поиске несуществующей книги по её идентификатору")
    @Test
    void shouldThrowExceptionOnNonExistingBookById() {
        long bookId = 0L;
        given(bookRepository.findById(bookId)).willReturn(Optional.empty());
        assertThatCode(() -> bookService.findById(bookId)).isInstanceOf(LibraryException.class);
    }

    @DisplayName("удалять книгу по её идентификатору")
    @Test
    void shouldDeleteBookById() {
        Book book = new Book(1L);
        given(bookRepository.getById(anyLong()))
                .will(invocation -> new Book(invocation.getArgument(0)));

        bookService.deleteById(1L);
        verify(bookRepository).delete(book);
    }

    @DisplayName("обновлять книгу новыми значениями, в т.ч. идентификаторами автора и жанра")
    @Test
    void shouldUpdateWithIds() {
        BookWithIdsSaveRequest bookWithIdsSaveRequest = new BookWithIdsSaveRequest(100L, "Title", existingAuthor.getId(), existingGenre.getId());

        given(authorRepository.findById(bookWithIdsSaveRequest.getAuthorId()))
                .willReturn(Optional.of(existingAuthor));
        given(genreRepository.findById(bookWithIdsSaveRequest.getGenreId()))
                .willReturn(Optional.of(existingGenre));
        given(bookRepository.getById(anyLong()))
                .will(invocation -> new Book(invocation.getArgument(0)));
        given(bookRepository.save(any()))
                .will(invocation -> invocation.getArgument(0));

        Book actualBook = bookService.save(bookWithIdsSaveRequest);
        assertThat(actualBook.getTitle()).isEqualTo(bookWithIdsSaveRequest.getTitle());
        assertThat(actualBook.getAuthor().getId()).isEqualTo(bookWithIdsSaveRequest.getAuthorId());
        assertThat(actualBook.getGenre().getId()).isEqualTo(bookWithIdsSaveRequest.getGenreId());
    }


    @DisplayName("обновлять книгу новыми значениями, в т.ч. именами автора и жанра")
    @Test
    void shouldUpdateWithNames() {
        BookSaveRequest bookSaveRequest = new BookSaveRequest(100L, "Title", newAuthor.getName(), newGenre.getName());

        given(authorRepository.findByName(bookSaveRequest.getAuthor()))
                .willReturn(Collections.emptyList());
        given(genreRepository.findByName(bookSaveRequest.getGenre()))
                .willReturn(Collections.emptyList());
        given(authorRepository.save(any()))
                .will(invocation -> invocation.getArgument(0));
        given(genreRepository.save(any()))
                .will(invocation -> invocation.getArgument(0));
        given(bookRepository.getById(anyLong()))
                .will(invocation -> new Book(invocation.getArgument(0)));
        given(bookRepository.save(any()))
                .will(invocation -> invocation.getArgument(0));

        Book actualBook = bookService.save(bookSaveRequest);
        assertThat(actualBook.getTitle()).isEqualTo(bookSaveRequest.getTitle());
        assertThat(actualBook.getAuthor().getName()).isEqualTo(bookSaveRequest.getAuthor());
        assertThat(actualBook.getGenre().getName()).isEqualTo(bookSaveRequest.getGenre());
    }

}