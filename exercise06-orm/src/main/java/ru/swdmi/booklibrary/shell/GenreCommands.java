package ru.swdmi.booklibrary.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.swdmi.booklibrary.service.GenreService;
import ru.swdmi.booklibrary.shell.dto.GenreDTO;
import ru.swdmi.booklibrary.shell.dto.mapper.GenreMapper;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @ShellMethod(value = "Read all genres", key = {"rg", "readGenres"})
    public List<GenreDTO> readAllGenres() {
        return genreService.findAll().stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

}
