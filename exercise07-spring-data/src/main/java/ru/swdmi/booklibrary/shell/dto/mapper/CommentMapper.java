package ru.swdmi.booklibrary.shell.dto.mapper;

import org.mapstruct.Mapper;
import ru.swdmi.booklibrary.domain.Comment;
import ru.swdmi.booklibrary.shell.dto.CommentDTO;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO toDTO(Comment comment);
}
