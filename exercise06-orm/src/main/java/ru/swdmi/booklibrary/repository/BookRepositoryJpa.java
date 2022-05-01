package ru.swdmi.booklibrary.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import ru.swdmi.booklibrary.domain.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public Optional<Book> findByIdWithLazyAll(long id) {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-genres-entity-graph");
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", entityGraph);
        Book book = em.find(Book.class, id, properties);
        if (book != null) {
            Hibernate.initialize(book.getComments());
        }
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<Book> findByIdWithLazyComments(long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            Hibernate.initialize(book.getComments());
        }
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findByName(String bookTitle) {
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from Book b " +
                        "where b.title = :bookTitle",
                Book.class);
        query.setParameter("bookTitle", bookTitle);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.isNew()) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void delete(long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.remove(book);
        }
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-genres-entity-graph");
        TypedQuery<Book> selectQuery = em.createQuery("select b from Book b", Book.class);
        selectQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        return selectQuery.getResultList();
    }
}
