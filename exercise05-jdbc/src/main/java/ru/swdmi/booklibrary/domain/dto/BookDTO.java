package ru.swdmi.booklibrary.domain.dto;

import lombok.Getter;

public class BookDTO {
    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private String author;
    @Getter
    private String genre;

    public BookDTO(Long id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public BookDTO(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
