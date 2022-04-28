package ru.swdmi.booklibrary.service;

import org.springframework.stereotype.Service;
import ru.swdmi.booklibrary.domain.Author;
import ru.swdmi.booklibrary.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
