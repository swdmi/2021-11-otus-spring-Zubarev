package ru.swdmi.booklibrary.service.model;

import lombok.Getter;

@Getter
public class CommentSaveRequest {
    private Long id;
    private final String text;
    private String bookTitle;

    public CommentSaveRequest(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public CommentSaveRequest(String text, String bookTitle) {
        this.text = text;
        this.bookTitle = bookTitle;
    }

    public boolean isCreatable() {
        return this.id == null && this.bookTitle != null;
    }
}
