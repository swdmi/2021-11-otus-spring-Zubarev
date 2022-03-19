package ru.swdmi.booklibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.service.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {
    private final AuthorService authorService;

    @ShellMethod(value = "Read all authors", key = {"ra", "readAuthors"})
    public List<Author> readAllAuthors() {
        return authorService.findAll();
    }

}
