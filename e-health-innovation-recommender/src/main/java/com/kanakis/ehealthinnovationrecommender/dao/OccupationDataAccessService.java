package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Occupation;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres-occupation")
public class OccupationDataAccessService implements OccupationDao {

    private final JdbcTemplate jdbcTemplate;

    public OccupationDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertOccupation(Occupation tag) {
        String sql = "INSERT"
                + " INTO ehir_occupation "
                + "(name) "
                + "VALUES "
                + "(?)";
        jdbcTemplate.update(sql, tag.getName());
        return 1;
    }

    @Override
    public int deleteOccupationById(int id) {
        String sql = "DELETE"
                + " FROM ehir_occupation"
                + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public List<Occupation> selectAllOccupations() {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_occupation"
                + " ORDER BY name ASC";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Occupation(id, name);
        });
    }

    @Override
    public Optional<Occupation> selectOccupationByID(int id) {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_occupation"
                + " WHERE id = ?";
        Occupation occupation = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            int occupationId = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Occupation(occupationId, name);
        });
        return Optional.ofNullable(occupation);
    }

    @Override
    public Optional<Occupation> selectOccupationByName(String name) {
        try {
            String sql = "SELECT"
                    + " id,"
                    + " name"
                    + " FROM ehir_occupation"
                    + " WHERE name = ?";
            Occupation existingTag = jdbcTemplate.queryForObject(sql, new Object[]{name}, (resultSet, i) -> {
                String occupationName = resultSet.getString("name");
                int occupationId = Integer.parseInt(resultSet.getString("id"));
                return new Occupation(occupationId, occupationName);
            });
            return Optional.ofNullable(existingTag);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int updateOccupationByID(int id, Occupation occupation) {
        String sql = "UPDATE "
                + "ehir_occupation"
                + " SET name = ?"
                + " WHERE id = ?";
        jdbcTemplate.update(sql, occupation.getName(), id);
        return 1;
    }

    @Override
    public int insertTagRelationShip(int id, Tag tag) {
        String sqlItemTagRelationInjection = "INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (?,?)";
        jdbcTemplate.update(sqlItemTagRelationInjection, id, tag.getId());
        System.out.println("connection made between occupation: " + id + " and Tag: " + tag.getName());
        return 1;
    }

    public List<Tag> getOccupationTags(int departmentId) {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_occupation_tag"
                + " WHERE id = (?)"
                + " ORDER BY name ASC";
        return jdbcTemplate.query(sql, new Object[] { departmentId }, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Tag(id, name);
        });
    }
}
