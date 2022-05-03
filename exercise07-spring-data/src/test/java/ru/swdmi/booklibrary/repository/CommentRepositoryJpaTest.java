package ru.swdmi.booklibrary.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Comment;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с комментариями должен ")
@DataJpaTest
public class CommentRepositoryJpaTest {
    private static final long EXISTED_BOOK_ID = 100L;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TestEntityManager em;

    @DisplayName("возвращать комментарий по его идентификатору")
    @Test
    void shouldReturnById() {
        Book existedBook = em.find(Book.class, EXISTED_BOOK_ID);

        Comment expectedComment = new Comment("Text of comment", existedBook);
        em.persist(expectedComment);

        em.flush();
        em.clear();

        Optional<Comment> actualComment = commentRepository.findById(expectedComment.getId()).stream().findFirst();

        assertThat(actualComment).isPresent().get()
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(expectedComment);
    }
}
