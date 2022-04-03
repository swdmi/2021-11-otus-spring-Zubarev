package ru.swdmi.booklibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {
    private final GenreService genreService;

    @ShellMethod(value = "Read all genres", key = {"rg", "readGenres"})
    public List<Genre> readAllGenres() {
        return genreService.findAll();
    }

}
