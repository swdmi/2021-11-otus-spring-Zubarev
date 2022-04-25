package ru.swdmi.booklibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "genres")
public class Genre extends AbstractIdentityIdEntity<Long>{
    @Getter
    @Column
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Long id, String name) {
        setId(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
