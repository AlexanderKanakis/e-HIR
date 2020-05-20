package com.kanakis.ehealthinnovationrecommender.service;

import com.kanakis.ehealthinnovationrecommender.dao.UserExternalDao;
import com.kanakis.ehealthinnovationrecommender.dao.UserInternalDao;
import com.kanakis.ehealthinnovationrecommender.model.UserExternal;
import com.kanakis.ehealthinnovationrecommender.model.UserInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserExternalService {
    private final UserExternalDao userExternalDao;

    @Autowired
    public UserExternalService(@Qualifier("postgres-user-external") UserExternalDao userExternalDao) {
        this.userExternalDao = userExternalDao;
    }

    public int addUser(UserExternal user) {
        UUID id = UUID.randomUUID();
        return userExternalDao.insertUser(id, user);
    }

    public List<UserExternal> getAllUsers() {
        return  userExternalDao.selectAllUsers();
    }

    public Optional<UserExternal> getUserByID(UUID id) {
        return  userExternalDao.selectUserById(id);
    }

    public int deleteUser(UUID id) {
        return  userExternalDao.deleteUserById(id);
    }

    public int updateUser(UUID id, UserExternal user) {
        return userExternalDao.updateUserByID(id, user);
    }


}
