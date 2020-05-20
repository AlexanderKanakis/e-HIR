package com.kanakis.ehealthinnovationrecommender.controller;

import com.kanakis.ehealthinnovationrecommender.model.Department;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import com.kanakis.ehealthinnovationrecommender.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/department")
@RestController
@CrossOrigin
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public void addTag (@RequestBody @Valid @NotNull Department department) {
        departmentService.addDepartment(department);
    }

    @PostMapping(path = "{id}/tags")
    public int insertTagRelationShip( @PathVariable("id") int id,@RequestBody @Valid @NotNull Tag tag) {
        return departmentService.insertTagRelationShip(id, tag);
    }

    @GetMapping(path = "{id}")
    public Optional<Department> getDepartmentById (@PathVariable("id") int id) {
        return departmentService.selectDepartmentsById(id);
    }

    @GetMapping
    public List<Department> getAllTags () {
        return departmentService.selectAllDepartments();
    }

    @DeleteMapping(path = "{id}")
    public int deleteTagById (@PathVariable("id") int id) {
        departmentService.removeDepartmentById(id);
        return 1;
    }

    @PutMapping(path = "{id}")
    public int updateTagById (@PathVariable("id") int id, @RequestBody @Valid @NotNull Department department) {
        return departmentService.updateDepartmentById(id, department);
    }

    @GetMapping(path = "{id}/tags")
    public List<Tag> getDepartmentTags (@PathVariable("id") int id) {
        return departmentService.getDepartmentTags(id);
    }
}
