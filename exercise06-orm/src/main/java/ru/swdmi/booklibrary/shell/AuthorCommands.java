package ru.swdmi.booklibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.swdmi.booklibrary.service.AuthorService;
import ru.swdmi.booklibrary.shell.dto.AuthorDTO;
import ru.swdmi.booklibrary.shell.dto.mapper.AuthorMapper;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @ShellMethod(value = "Read all authors", key = {"ra", "readAuthors"})
    public List<AuthorDTO> readAllAuthors() {
        return authorService.findAll().stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

}
