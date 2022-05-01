package ru.swdmi.booklibrary.shell.dto.mapper;

import org.mapstruct.Mapper;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.shell.dto.BookDTO;
import ru.swdmi.booklibrary.shell.dto.BookFullInfoDTO;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, GenreMapper.class, CommentMapper.class})
public interface BookMapper {
    BookDTO toDTO(Book book);
    BookFullInfoDTO toFullInfoDTO(Book book);
}
