package ru.swdmi.booklibrary.service.model;

import lombok.Getter;

@Getter
public class BookWithIdsSaveRequest extends AbstractBookSaveRequest {
    private final Long authorId;
    private final Long genreId;

    public BookWithIdsSaveRequest(Long id, String title, Long authorId, Long genreId) {
        super(id, title);
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public BookWithIdsSaveRequest(String title, Long authorId, Long genreId) {
        super(title);
        this.authorId = authorId;
        this.genreId = genreId;
    }
}
