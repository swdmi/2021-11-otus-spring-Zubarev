package ru.swdmi.booklibrary.shell.dto.mapper;

import org.mapstruct.Mapper;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.shell.dto.AuthorDTO;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDTO(Author author);
}
