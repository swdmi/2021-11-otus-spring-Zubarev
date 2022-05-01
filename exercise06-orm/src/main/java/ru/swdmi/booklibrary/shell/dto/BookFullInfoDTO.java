package ru.swdmi.booklibrary.shell.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookFullInfoDTO {
    private long id;
    private String title;
    private AuthorDTO author;
    private GenreDTO genre;
    private List<CommentDTO> comments;
}
