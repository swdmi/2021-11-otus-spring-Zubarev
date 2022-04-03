package ru.swdmi.booklibrary.domain;

import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class Book {
    @EqualsAndHashCode.Include
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private Author author;
    @Getter
    @Setter
    private Genre genre;
}
