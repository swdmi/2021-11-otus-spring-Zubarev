package ru.swdmi.booklibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "comments")
public class Comment extends AbstractIdentityIdEntity<Long> {

    @Setter
    @Column
    private String text;

    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    public Comment(long id) {
        setId(id);
    }

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
