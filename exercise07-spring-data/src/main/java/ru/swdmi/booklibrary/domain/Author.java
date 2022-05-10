package ru.swdmi.booklibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author extends AbstractIdentityIdEntity<Long> {

    @Getter
    @Column
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name) {
        setId(id);
        this.name = name;
    }
}


