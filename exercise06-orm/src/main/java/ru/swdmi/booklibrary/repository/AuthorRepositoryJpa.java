package ru.swdmi.booklibrary.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.swdmi.booklibrary.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a " +
                        "from Author a " +
                        "where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author save(Author author) {
        if (author.isNew()) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }
}
