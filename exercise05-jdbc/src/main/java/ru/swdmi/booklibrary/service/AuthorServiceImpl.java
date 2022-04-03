package ru.swdmi.booklibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.swdmi.booklibrary.dao.AuthorDao;
import ru.swdmi.booklibrary.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorDao.findAll();
    }
}
