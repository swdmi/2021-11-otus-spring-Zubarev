package ru.swdmi.booklibrary.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Genre {
    @EqualsAndHashCode.Include
    @Getter
    @Setter
    private Long id;
    @Getter
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
