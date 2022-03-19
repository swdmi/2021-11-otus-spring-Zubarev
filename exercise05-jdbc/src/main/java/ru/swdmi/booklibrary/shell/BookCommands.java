package ru.swdmi.booklibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.dto.BookDTO;
import ru.swdmi.booklibrary.domain.dto.BookWithIdsDTO;
import ru.swdmi.booklibrary.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;

    @ShellMethod(value = "Create book: book title, author id, genre id", key = {"cb", "createBook"})
    public String createBook(@ShellOption String bookTitle, @ShellOption String authorId, @ShellOption String genreId) {
        Book book = bookService.save(new BookWithIdsDTO(bookTitle, Long.parseLong(authorId), Long.parseLong(genreId)));
        return String.format("Book successfully created: %s", book);
    }

    @ShellMethod(value = "Create book with new author and/or genre: book title, author name, genre name", key = {"cbn", "createBookWithNames"})
    public String createBookWithNames(@ShellOption String bookTitle, @ShellOption String authorName, @ShellOption String genreName) {
        Book book = bookService.save(new BookDTO(bookTitle, authorName, genreName));
        return String.format("Book successfully created: %s", book);
    }

    @ShellMethod(value = "Read all books", key = {"rb", "readBooks"})
    public List<Book> readAllBooks() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Read book by id", key = {"rbid", "readBookById"})
    public Book readBookById(@ShellOption Long id) {
        return bookService.findById(id);
    }

    @ShellMethod(value = "Update book: book id, book title, author id, genre id", key = {"ub", "updateBook"})
    public String updateBook(@ShellOption Long bookId, @ShellOption String bookTitle, @ShellOption String authorId, @ShellOption String genreId) {
        Book book = bookService.update(new BookWithIdsDTO(bookId, bookTitle, Long.parseLong(authorId), Long.parseLong(genreId)));
        return String.format("Book successfully updated: %s", book);
    }

    @ShellMethod(value = "Update book with new author and/or genre: book title, author name, genre name", key = {"ubn", "updateBookWithNames"})
    public String createBookWithNames(@ShellOption Long bookId, @ShellOption String bookTitle, @ShellOption String authorName, @ShellOption String genreName) {
        Book book = bookService.update(new BookDTO(bookId, bookTitle, authorName, genreName));
        return String.format("Book successfully updated: %s", book);
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "deleteBook"})
    public String deleteBook(@ShellOption Long id) {
        bookService.deleteById(id);
        return String.format("Book with id = %s was successfully deleted", id);
    }
}
