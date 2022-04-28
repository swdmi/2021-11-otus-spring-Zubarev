package ru.swdmi.booklibrary.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Comment;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с комментариями должен ")
@DataJpaTest
@Import({CommentRepositoryJpa.class})
public class CommentRepositoryJpaTest {
    private static final long FIRST_COMMENT_ID = 100L;
    private static final long SECOND_COMMENT_ID = 101L;
    private static final long RELATED_BOOK_ID = 100L;

    @Autowired
    CommentRepositoryJpa commentRepository;

    @Autowired
    TestEntityManager em;

    @DisplayName("добавлять комментарий в БД")
    @Test
    void shouldInsertComment() {
        Book book = em.find(Book.class, RELATED_BOOK_ID);
        Comment comment = new Comment("Text of new comment", book);
        commentRepository.save(comment);

        em.flush();
        em.clear();

        Comment actualComment = em.find(Comment.class, comment.getId());
        assertThat(actualComment).isEqualTo(comment);
        assertThat(actualComment.getText()).isEqualTo(comment.getText());
        assertThat(actualComment.getBook()).isEqualTo(comment.getBook());

    }

    @DisplayName("удалять комментарий по его идентификатору")
    @Test
    void shouldDeleteExistedComment() {
        commentRepository.delete(FIRST_COMMENT_ID);

        em.flush();
        em.clear();

        Optional<Comment> comment = Optional.ofNullable(em.find(Comment.class, FIRST_COMMENT_ID));
        assertThat(comment).isEmpty();
    }

    @DisplayName("редактировать существующий в БД комментарий")
    @Test
    void shouldUpdateExistedComment() {
        Comment comment = em.find(Comment.class, FIRST_COMMENT_ID);
        comment.setText("Edited text");
        commentRepository.save(comment);

        Comment actualComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(actualComment).isEqualTo(comment);
        assertThat(actualComment.getText()).isEqualTo(comment.getText());
    }

    @DisplayName("возвращать комментарий по его идентификатору")
    @Test
    void shouldReturnCommentById() {
        Optional<Comment> actualComment = commentRepository.findById(FIRST_COMMENT_ID);
        Comment expectedComment = em.find(Comment.class, FIRST_COMMENT_ID);

        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }
}
