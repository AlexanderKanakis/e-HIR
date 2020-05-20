package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.User;
import com.kanakis.ehealthinnovationrecommender.model.UserInternal;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserInternalDao {

    int insertUser(UUID id, UserInternal user);

    default int insertUser(UserInternal user) {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<UserInternal> selectAllUsers();

    int deleteUserById(UUID id);

    int updateUserByID(UUID id, UserInternal newUser);

    Optional<UserInternal> selectUserById(UUID id);

    Optional<UserInternal> loginUser(UserInternal user);
}
