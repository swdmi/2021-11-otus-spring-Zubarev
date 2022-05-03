package ru.swdmi.booklibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.swdmi.booklibrary.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
