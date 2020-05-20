package com.kanakis.ehealthinnovationrecommender.controller;

import com.kanakis.ehealthinnovationrecommender.model.User;
import com.kanakis.ehealthinnovationrecommender.model.UserInternal;
import com.kanakis.ehealthinnovationrecommender.service.UserInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/user/internal")
@RestController
@CrossOrigin
public class UserInternalController {

    private final UserInternalService userInternalService;

    @Autowired
    public UserInternalController(UserInternalService userInternalService) {
        this.userInternalService = userInternalService;
    }

    @PostMapping
    public void addUser(@Valid @NotNull @RequestBody UserInternal user) {
        userInternalService.addUser(user);
    }

    @PostMapping(path ="/login")
    public Optional<UserInternal> loginUser(@Valid @Null @RequestBody UserInternal user) {
        return userInternalService.loginUser(user);
    }

    @GetMapping
    public List<UserInternal> getAllUsers () {
        return userInternalService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById (@PathVariable("id") UUID id) {
        return userInternalService.getUserByID(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser (@PathVariable("id") UUID id) {
        userInternalService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser (@PathVariable("id") UUID id, @Valid @NotNull @RequestBody UserInternal user) {
        userInternalService.updateUser(id, user);
    }
}
