package ru.swdmi.booklibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.swdmi.booklibrary.domain.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);
}
