package com.kanakis.ehealthinnovationrecommender.service;

import com.kanakis.ehealthinnovationrecommender.dao.DepartmentDao;
import com.kanakis.ehealthinnovationrecommender.dao.TagDao;
import com.kanakis.ehealthinnovationrecommender.dao.TagDataAccessService;
import com.kanakis.ehealthinnovationrecommender.model.Department;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentService(@Qualifier("postgres-department") DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public int addDepartment(Department department) {
        return departmentDao.insertDepartment(department);
    }

    public int insertTagRelationShip(int id, Tag tag) {
        return departmentDao.insertTagRelationShip(id, tag);
    }

    public int removeDepartmentById(int id) {
        return departmentDao.deleteDepartmentById(id);
    }

    public  int updateDepartmentById(int id, Department department) {
        return departmentDao.updateDepartmentByID(id, department);
    }

    public List<Department> selectAllDepartments() {
        return departmentDao.selectAllDepartments();
    }

    public Optional<Department> selectDepartmentsById (int id) {
        return departmentDao.selectDepartmentByID(id);
    }

    public List<Tag> getDepartmentTags (int id) {
        return departmentDao.getDepartmentTags(id);
    }
}
