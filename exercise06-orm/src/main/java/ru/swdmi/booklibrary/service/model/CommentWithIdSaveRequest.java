package ru.swdmi.booklibrary.service.model;

import lombok.Getter;

@Getter
public class CommentWithIdSaveRequest {
    private Long id;
    private final String text;
    private final Long bookId;

    public CommentWithIdSaveRequest(String text, Long bookId) {
        this.text = text;
        this.bookId = bookId;
    }
}
