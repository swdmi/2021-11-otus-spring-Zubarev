package ru.swdmi.booklibrary.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.swdmi.booklibrary.dao.mapper.GenreMapper;
import ru.swdmi.booklibrary.domain.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final GenreMapper genreMapper;

    @Override
    public Optional<Genre> findById(long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select id, name from genres where id = :id",
                    Map.of("id", id), genreMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> findByName(String name) {
        return jdbcTemplate.query("select id, name from genres where name = :name",
                Map.of("name", name), genreMapper);
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query("select id, name from genres", genreMapper);
    }

    @Override
    public Genre insert(Genre genre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource(Map.of("name", genre.getName()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("insert into genres (name) values (:name);",
                paramSource, keyHolder);
        genre.setId(keyHolder.getKeyAs(Long.class));
        return genre;
    }
}
