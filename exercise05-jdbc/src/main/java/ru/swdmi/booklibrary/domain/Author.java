package ru.swdmi.booklibrary.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Author {
    @EqualsAndHashCode.Include
    @Getter
    @Setter
    private Long id;
    @Getter
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}


