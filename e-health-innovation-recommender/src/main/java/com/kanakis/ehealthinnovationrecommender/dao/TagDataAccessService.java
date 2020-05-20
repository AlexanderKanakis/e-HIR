package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres-tag")
public class TagDataAccessService implements TagDao {

    private final JdbcTemplate jdbcTemplate;

    public TagDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTag(Tag tag) {
        String sql = "INSERT"
                + " INTO ehir_tag "
                + "(name) "
                + "VALUES "
                + "(?)";
        jdbcTemplate.update(sql, tag.getName());
        return 1;
    }

    @Override
    public int deleteTagById(int id) {
        String sql = "DELETE"
                + " FROM ehir_tag"
                + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public List<Tag> selectAllTags() {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_tag"
                + " ORDER BY name ASC";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Tag(id, name);
        });
    }

    @Override
    public Optional<Tag> selectTagByID(int id) {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_tag"
                + " WHERE id = ?";
        Tag tag = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            int tagId = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Tag(tagId, name);
        });
        return Optional.ofNullable(tag);
    }

    @Override
    public Optional<Tag> selectTagByName(String name) {
        try {
            String sql = "SELECT"
                    + " id,"
                    + " name"
                    + " FROM ehir_tag"
                    + " WHERE name = ?";
            Tag existingTag = jdbcTemplate.queryForObject(sql, new Object[]{name}, (resultSet, i) -> {
                String tagName = resultSet.getString("name");
                int tagId = Integer.parseInt(resultSet.getString("id"));
                return new Tag(tagId, tagName);
            });
            return Optional.ofNullable(existingTag);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int updateTagByID(int id, Tag tag) {
        String sql = "UPDATE "
                + "ehir_tag"
                + " SET name = ?"
                + " WHERE id = ?";
        jdbcTemplate.update(sql, tag.getName(), id);
        return 1;
    }
}
