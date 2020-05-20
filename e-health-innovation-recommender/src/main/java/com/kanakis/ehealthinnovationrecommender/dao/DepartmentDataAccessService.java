package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Department;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres-department")
public class DepartmentDataAccessService implements DepartmentDao {

    private final JdbcTemplate jdbcTemplate;

    public DepartmentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertDepartment(Department department) {
        String sql = "INSERT"
                + " INTO ehir_department "
                + "(name) "
                + "VALUES "
                + "(?)";
        jdbcTemplate.update(sql, department.getName());
        return 1;
    }

    @Override
    public int deleteDepartmentById(int id) {
        String sql = "DELETE"
                + " FROM ehir_department"
                + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public List<Department> selectAllDepartments() {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_department"
                + " ORDER BY name ASC";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Department(id, name);
        });
    }

    @Override
    public Optional<Department> selectDepartmentByID(int id) {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_department"
                + " WHERE id = ?";
        Department department = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            int departmentId = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Department(departmentId, name);
        });
        return Optional.ofNullable(department);
    }

    @Override
    public int updateDepartmentByID(int id, Department department) {
        String sql = "UPDATE "
                + "ehir_department"
                + " SET name = ?"
                + " WHERE id = ?";
        jdbcTemplate.update(sql, department.getName(), id);
        return 1;
    }

    @Override
    public int insertTagRelationShip(int id, Tag tag) {
        String sqlItemTagRelationInjection = "INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (?,?)";
        jdbcTemplate.update(sqlItemTagRelationInjection, id, tag.getId());
        return 1;
    }

    public List<Tag> getDepartmentTags(int departmentId) {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_department_tag"
                + " WHERE id = (?)"
                + " ORDER BY name ASC";
        return jdbcTemplate.query(sql, new Object[] { departmentId }, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Tag(id, name);
        });
    }
}
