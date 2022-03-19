package ru.swdmi.booklibrary.domain.dto;

import lombok.Getter;

public class BookWithIdsDTO {
    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private Long authorId;
    @Getter
    private Long genreId;

    public BookWithIdsDTO(Long id, String title, Long authorId, Long genreId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public BookWithIdsDTO(String title, Long authorId, Long genreId) {
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
    }
}
