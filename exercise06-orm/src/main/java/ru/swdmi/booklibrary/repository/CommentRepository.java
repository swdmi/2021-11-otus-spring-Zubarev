package ru.swdmi.booklibrary.repository;

import ru.swdmi.booklibrary.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(long id);
    List<Comment> findByBookId(long bookId);
    void delete(Long id);
}
