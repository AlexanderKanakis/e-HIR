package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.UserExternal;
import com.kanakis.ehealthinnovationrecommender.model.UserInternal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserExternalDao {

    int insertUser(UUID id, UserExternal user);

    default int insertUser(UserExternal user) {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<UserExternal> selectAllUsers();

    int deleteUserById(UUID id);

    int updateUserByID(UUID id, UserExternal newUser);

    Optional<UserExternal> selectUserById(UUID id);

}
