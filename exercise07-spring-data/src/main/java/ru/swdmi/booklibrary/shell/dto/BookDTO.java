package ru.swdmi.booklibrary.shell.dto;

import lombok.Data;

@Data
public class BookDTO {
    private long id;
    private String title;
    private AuthorDTO author;
    private GenreDTO genre;
}
