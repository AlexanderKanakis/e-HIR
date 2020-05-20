package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Department;
import com.kanakis.ehealthinnovationrecommender.model.Tag;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {

    int insertDepartment(Department department);

    List<Department> selectAllDepartments();

    Optional<Department> selectDepartmentByID(int id);

    int deleteDepartmentById(int id);

    int updateDepartmentByID(int id, Department department);

    int insertTagRelationShip(int id, Tag tag);

    List<Tag> getDepartmentTags(int id);
}
