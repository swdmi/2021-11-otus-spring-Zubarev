package ru.swdmi.booklibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.swdmi.booklibrary.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByName(String name);
}
