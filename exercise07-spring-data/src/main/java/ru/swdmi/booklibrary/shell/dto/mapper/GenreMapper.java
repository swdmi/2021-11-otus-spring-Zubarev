package ru.swdmi.booklibrary.shell.dto.mapper;

import org.mapstruct.Mapper;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.shell.dto.GenreDTO;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDTO toDTO(Genre genre);
}
