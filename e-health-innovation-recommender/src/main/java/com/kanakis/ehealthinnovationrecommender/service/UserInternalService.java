package com.kanakis.ehealthinnovationrecommender.service;

import com.kanakis.ehealthinnovationrecommender.dao.UserInternalDao;
import com.kanakis.ehealthinnovationrecommender.model.User;
import com.kanakis.ehealthinnovationrecommender.model.UserInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInternalService {
    private final UserInternalDao userInternalDao;

    @Autowired
    public UserInternalService(@Qualifier("postgres-user-internal") UserInternalDao userInternalDao) {
        this.userInternalDao = userInternalDao;
    }

    public int addUser(UserInternal user) {
        UUID id = UUID.randomUUID();
        return userInternalDao.insertUser(id, user);
    }

    public List<UserInternal> getAllUsers() {
        return  userInternalDao.selectAllUsers();
    }

    public Optional<UserInternal> getUserByID(UUID id) {
        return  userInternalDao.selectUserById(id);
    }

    public int deleteUser(UUID id) {
        return  userInternalDao.deleteUserById(id);
    }

    public int updateUser(UUID id, UserInternal user) {
        return userInternalDao.updateUserByID(id, user);
    }

    public Optional<UserInternal> loginUser(UserInternal user) {
        return userInternalDao.loginUser(user);
    }

}
