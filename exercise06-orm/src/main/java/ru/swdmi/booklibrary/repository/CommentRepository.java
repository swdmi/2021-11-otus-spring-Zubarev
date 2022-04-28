package ru.swdmi.booklibrary.repository;

import ru.swdmi.booklibrary.domain.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(long id);
    void delete(Long id);
}
