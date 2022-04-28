package ru.swdmi.booklibrary.service;

import org.springframework.stereotype.Service;
import ru.swdmi.booklibrary.domain.Genre;
import ru.swdmi.booklibrary.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
