package ru.swdmi.booklibrary.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.swdmi.booklibrary.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select g " +
                        "from Genre g " +
                        "where g.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.isNew()) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }
}
