package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Country;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres-country")
public class CountryDataAccessService implements CountryDao {

    private final JdbcTemplate jdbcTemplate;

    public CountryDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCountry(Country country) {
        String sql = "INSERT"
                + " INTO ehir_country "
                + "(name) "
                + "VALUES "
                + "(?)";
        jdbcTemplate.update(sql, country.getName());
        return 1;
    }

    @Override
    public int deleteCountryById(int id) {
        String sql = "DELETE"
                + " FROM ehir_country"
                + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public List<Country> selectAllCountries() {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_country"
                + " ORDER BY name ASC";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Country(id, name);
        });
    }

    @Override
    public Optional<Country> selectCountryByID(int id) {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_country"
                + " WHERE id = (?)";
        Country country = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            int countryId = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Country(countryId, name);
        });
        return Optional.ofNullable(country);
    }

    @Override
    public int insertTagRelationShip(int id, Tag tag) {
        String sqlItemTagRelationInjection = "INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (?,?)";
        jdbcTemplate.update(sqlItemTagRelationInjection, id, tag.getId());
        System.out.println("connection made between country: " + id + " and Tag: " + tag.getName());
        return 1;
    }

    public List<Tag> selectCountryTags(int countryId) {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_country_tag"
                + " WHERE id = (?)"
                + " ORDER BY name ASC";
        return jdbcTemplate.query(sql, new Object[] { countryId }, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Tag(id, name);
        });
    }
}
