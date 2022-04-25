package ru.swdmi.booklibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends AbstractIdentityIdEntity<Long> {

    @Getter
    @Setter
    @Column
    private String text;

    @Getter
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;


    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    public Comment(Long id, String text, Book book) {
        setId(id);
        this.text = text;
        this.book = book;
    }
}
