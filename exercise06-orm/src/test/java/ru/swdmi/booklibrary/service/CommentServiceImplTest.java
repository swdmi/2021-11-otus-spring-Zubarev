package ru.swdmi.booklibrary.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.domain.Book;
import ru.swdmi.booklibrary.domain.Comment;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.repository.BookRepository;
import ru.swdmi.booklibrary.repository.CommentRepository;
import ru.swdmi.booklibrary.service.model.CommentSaveRequest;
import ru.swdmi.booklibrary.service.model.CommentWithIdSaveRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для работы с комментариями должен ")
@SpringBootTest(classes = CommentServiceImpl.class)
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CommentRepository commentRepository;

    private static Book existingBook;
    private static Comment existingComment;

    @BeforeAll
    static void setUpClass() {
        existingBook = new Book(1L, "Book name", new Author(1L, "Somebody"), new Genre(1L, "Somewhat"));
        existingComment = new Comment(1L, "Text of comment", existingBook);
        existingBook.getComments().add(existingComment);
    }

    @DisplayName("сохранять новый комментарий с указанием идентификаторов автора и жанра")
    @Test
    void shouldSaveNewCommentWithBookId() {
        CommentWithIdSaveRequest commentSaveRequest = new CommentWithIdSaveRequest("text of comment", 1L);

        given(bookRepository.findById(1L)).willReturn(Optional.of(existingBook));
        given(commentRepository.save(any(Comment.class))).will(invocation -> invocation.getArgument(0));

        Comment comment = commentService.save(commentSaveRequest);
        assertThat(comment.getText()).isEqualTo(commentSaveRequest.getText());
        assertThat(comment.getBook().getId()).isEqualTo(commentSaveRequest.getBookId());
    }

    @DisplayName("сохранять новую книгу с указанием наименования книги")
    @Test
    void shouldSaveNewCommentWithBookName() {
        CommentSaveRequest commentSaveRequest = new CommentSaveRequest("text of comment", "Book name");

        given(bookRepository.findByName("Book name")).willReturn(List.of(existingBook));
        given(commentRepository.save(any(Comment.class))).will(invocation -> invocation.getArgument(0));

        Comment comment = commentService.save(commentSaveRequest);
        assertThat(comment.getText()).isEqualTo(commentSaveRequest.getText());
        assertThat(comment.getBook().getTitle()).isEqualTo(commentSaveRequest.getBookTitle());
    }

    @DisplayName("должен обновлять комментарий по его идентификатору")
    @Test
    void shouldUpdateCommentWithBookName() {
        CommentSaveRequest commentSaveRequest = new CommentSaveRequest(1L, "New text");

        given(commentRepository.findById(existingComment.getId())).willReturn(Optional.of(existingComment));
        given(commentRepository.save(any(Comment.class))).will(invocation -> invocation.getArgument(0));

        Comment comment = commentService.save(commentSaveRequest);
        assertThat(comment.getText()).isEqualTo(commentSaveRequest.getText());
    }

    @DisplayName("возвращать комментарий по его идентификатору")
    @Test
    void shouldReturnExistingCommentById() {
        given(commentRepository.findById(existingComment.getId())).willReturn(Optional.of(existingComment));

        Comment actualComment = commentService.findById(existingComment.getId());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(existingComment);
    }

    @DisplayName("возращать все комментарии, относящиеся к одной книге")
    @Test
    void shouldReturnExistingCommentsByBookId() {
        given(bookRepository.findByIdWithLazyComments(existingBook.getId())).willReturn(Optional.of(existingBook));

        List<Comment> actualCommentList = commentService.findByBookId(existingBook.getId());
        assertThat(actualCommentList).containsExactly(existingComment);
    }

    @DisplayName("удалять комментарий по его идентификатору")
    @Test
    void shouldDeleteCommentById() {
        long commentId = 1L;
        commentService.deleteById(commentId);
        verify(commentRepository).delete(commentId);
    }
}