package ru.swdmi.booklibrary.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.swdmi.booklibrary.dao.mapper.AuthorMapper;
import ru.swdmi.booklibrary.domain.Author;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AuthorMapper authorMapper;

    @Override
    public Optional<Author> findById(long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select id, name from authors where id = :id",
                    Map.of("id", id), authorMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> findByName(String name) {
        return jdbcTemplate.query("select id, name from authors where name = :name",
                Map.of("name", name), authorMapper);
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query("select id, name from authors", authorMapper);
    }

    @Override
    public Author insert(Author author) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource(Map.of("name", author.getName()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("insert into authors (name) values (:name);",
                paramSource, keyHolder);
        author.setId(keyHolder.getKeyAs(Long.class));
        return author;
    }
}
