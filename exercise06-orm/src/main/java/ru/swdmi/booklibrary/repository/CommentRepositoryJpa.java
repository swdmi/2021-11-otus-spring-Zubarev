package ru.swdmi.booklibrary.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.swdmi.booklibrary.domain.Comment;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {
    private final EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.isNew()) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void delete(Long id) {
        Comment comment = em.find(Comment.class, id);
        if (comment != null) {
            em.remove(comment);
        }
    }
}
