package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.UserExternal;
import com.kanakis.ehealthinnovationrecommender.model.UserInternal;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-user-external")
public class UserExternalDataAccessService implements UserExternalDao {

    private final JdbcTemplate jdbcTemplate;

    public UserExternalDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertUser(UUID id, UserExternal user) {
        final String sql = "Insert "
                + " INTO ehir_user_external "
                + " (id,"
                + "country, "
                + "department, "
                + "occupation, "
                + ") "
                + "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, user.getCountryID(), user.getDepartmentID(), user.getOccupationID());
        return 1;
    }

    @Override
    public List<UserExternal> selectAllUsers() {
        final String sql = "SELECT * "
                + " FROM ehir_user_external";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id =  UUID.fromString(resultSet.getString("id"));
            int countryID = Integer.parseInt(resultSet.getString("country"));
            int departmentID = Integer.parseInt(resultSet.getString("department"));
            int occupationID = Integer.parseInt(resultSet.getString("occupation"));
            return new UserExternal(id, countryID, departmentID, occupationID);
        });
    }

    @Override
    public int deleteUserById(UUID id) {
        final String sql = "DELETE FROM "
                + "  ehir_user_external "
                + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int updateUserByID(UUID id, UserExternal newUser) {
        final String sql = "UPDATE ehir_user_external"
                + " SET country = ?, "
                + "department = ?, "
                + "occupation = ? "
                + "WHERE id = ?";
        jdbcTemplate.update(
                sql,
                newUser.getCountryID(),
                newUser.getDepartmentID(),
                newUser.getOccupationID(),
                id);
        return 1;
    }

    @Override
    public Optional<UserExternal> selectUserById(UUID id) {
        final String sql = "SELECT "
                + "id, "
                + "name, "
                + "email, "
                + "FROM ehir_user_external "
                + "WHERE id = ?";
        UserExternal user =  jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    int countryID = Integer.parseInt(resultSet.getString("country"));
                    int departmentID = Integer.parseInt(resultSet.getString("department"));
                    int occupationID = Integer.parseInt(resultSet.getString("occupation"));
                    return new UserExternal(personId, countryID, departmentID, occupationID);
                });
        return Optional.ofNullable(user);
    }
}
