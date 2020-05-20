package com.kanakis.ehealthinnovationrecommender.controller;

import com.kanakis.ehealthinnovationrecommender.model.User;
import com.kanakis.ehealthinnovationrecommender.model.UserExternal;
import com.kanakis.ehealthinnovationrecommender.model.UserInternal;
import com.kanakis.ehealthinnovationrecommender.service.UserExternalService;
import com.kanakis.ehealthinnovationrecommender.service.UserInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/user/external")
@RestController
@CrossOrigin
public class UserExternalController {

    private final UserExternalService userExternalService;

    @Autowired
    public UserExternalController(UserExternalService userExternalService) {
        this.userExternalService = userExternalService;
    }

    @PostMapping
    public void addUser(@Valid @NotNull @RequestBody UserExternal user) {
        userExternalService.addUser(user);
    }

    @GetMapping
    public List<UserExternal> getAllUsers () {
        return userExternalService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById (@PathVariable("id") UUID id) {
        return userExternalService.getUserByID(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser (@PathVariable("id") UUID id) {
        userExternalService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser (@PathVariable("id") UUID id, @Valid @NotNull @RequestBody UserExternal user) {
        userExternalService.updateUser(id, user);
    }
}
