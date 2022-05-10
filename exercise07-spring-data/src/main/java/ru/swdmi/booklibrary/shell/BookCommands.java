package ru.swdmi.booklibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.service.BookService;
import ru.swdmi.booklibrary.service.model.BookSaveRequest;
import ru.swdmi.booklibrary.service.model.BookWithIdsSaveRequest;
import ru.swdmi.booklibrary.shell.dto.BookDTO;
import ru.swdmi.booklibrary.shell.dto.BookFullInfoDTO;
import ru.swdmi.booklibrary.shell.dto.mapper.BookMapper;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @ShellMethod(value = "Create book: book title, author id, genre id", key = {"cb", "createBook"})
    public String createBook(@ShellOption String bookTitle, @ShellOption String authorId, @ShellOption String genreId) {
        Book book = bookService.save(new BookWithIdsSaveRequest(bookTitle, Long.parseLong(authorId), Long.parseLong(genreId)));
        return String.format("Book successfully created: %s", bookMapper.toDTO(book));
    }

    @ShellMethod(value = "Create book with new author and/or genre: book title, author name, genre name", key = {"cbn", "createBookWithNames"})
    public String createBookWithNames(@ShellOption String bookTitle, @ShellOption String authorName, @ShellOption String genreName) {
        Book book = bookService.save(new BookSaveRequest(bookTitle, authorName, genreName));
        return String.format("Book successfully created: %s", bookMapper.toDTO(book));
    }

    @ShellMethod(value = "Read all books", key = {"rb", "readBooks"})
    public List<BookDTO> readAllBooks() {
        return bookService.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @ShellMethod(value = "Read book by id", key = {"rbid", "readBookById"})
    public BookFullInfoDTO readBookById(@ShellOption Long id) {
        Book book = bookService.findById(id);
        return bookMapper.toFullInfoDTO(book);
    }

    @ShellMethod(value = "Update book: book id, book title, author id, genre id", key = {"ub", "updateBook"})
    public BookDTO updateBook(@ShellOption Long bookId, @ShellOption String bookTitle, @ShellOption String authorId, @ShellOption String genreId) {
        Book book = bookService.save(new BookWithIdsSaveRequest(bookId, bookTitle, Long.parseLong(authorId), Long.parseLong(genreId)));
        return bookMapper.toDTO(book);
    }

    @ShellMethod(value = "Update book with new author and/or genre: book title, author name, genre name", key = {"ubn", "updateBookWithNames"})
    public BookDTO updateBookWithNames(@ShellOption Long bookId, @ShellOption String bookTitle, @ShellOption String authorName, @ShellOption String genreName) {
        Book book = bookService.save(new BookSaveRequest(bookId, bookTitle, authorName, genreName));
        return bookMapper.toDTO(book);
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "deleteBook"})
    public String deleteBook(@ShellOption Long id) {
        bookService.deleteById(id);
        return String.format("Book with id = %s was successfully deleted", id);
    }
}
