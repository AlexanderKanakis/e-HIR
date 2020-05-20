package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.UserInternal;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-user-internal")
public class UserInternalDataAccessService implements UserInternalDao {

    private final JdbcTemplate jdbcTemplate;

    public UserInternalDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserInternal> loginUser(UserInternal loginUser) {
        try {
            final String sql = "SELECT "
                    + "* "
                    + "FROM ehir_user_internal "
                    + "WHERE email = ? "
                    + "AND password = ?";
            UserInternal user = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{loginUser.getEmail(), loginUser.getPassword()},
                    (resultSet, i) -> {
                        UUID personId = UUID.fromString(resultSet.getString("id"));
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String password = resultSet.getString("password");
                        int privileges = Integer.parseInt(resultSet.getString("privileges"));

                        return new UserInternal(personId, name, email, password, privileges);
                    });
            return Optional.ofNullable(user);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int insertUser(UUID id, UserInternal user) {
        final String sql = "Insert "
                + " INTO ehir_user_internal "
                + " (id,"
                + " name, "
                + "email, "
                + "password, "
                + "privileges) "
                + "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, user.getName(), user.getEmail(), user.getPassword(), user.getPrivileges());
        return 1;
    }

    @Override
    public List<UserInternal> selectAllUsers() {
        final String sql = "SELECT * "
                + " FROM ehir_user_internal";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id =  UUID.fromString(resultSet.getString("id"));
            String name =  resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int privileges = Integer.parseInt(resultSet.getString("privileges"));
            return new UserInternal(id, name, email, password, privileges);
        });
    }

    @Override
    public int deleteUserById(UUID id) {
        final String sql = "DELETE FROM "
                + "  ehir_user_internal "
                + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int updateUserByID(UUID id, UserInternal newUser) {
        final String sql = "UPDATE ehir_user_internal"
                + " SET name = ?"
                + " WHERE id = ?";
        jdbcTemplate.update(
                sql,
                newUser.getName(),
                id);
        return 1;
    }

    @Override
    public Optional<UserInternal> selectUserById(UUID id) {
        final String sql = "SELECT "
                + "* "
                + "FROM ehir_user_internal "
                + "WHERE id = ?";
        UserInternal user =  jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    int privileges = Integer.parseInt(resultSet.getString("privileges"));
                    return new UserInternal(personId, name, email, password, privileges);
                });
        return Optional.ofNullable(user);
    }
}
