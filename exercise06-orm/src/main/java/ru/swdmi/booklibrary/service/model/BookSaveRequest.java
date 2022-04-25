package ru.swdmi.booklibrary.service.model;

import lombok.Getter;

@Getter
public class BookSaveRequest extends AbstractBookSaveRequest {
    private final String author;
    private final String genre;

    public BookSaveRequest(Long id, String title, String author, String genre) {
        super(id, title);
        this.author = author;
        this.genre = genre;
    }

    public BookSaveRequest(String title, String author, String genre) {
        super(title);
        this.author = author;
        this.genre = genre;
    }
}
