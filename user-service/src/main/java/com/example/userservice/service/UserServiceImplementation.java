package com.example.userservice.service;

import com.example.userservice.datasource.UserPseudoDataBase;
import com.example.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    private final UserPseudoDataBase userDb;

    @Autowired
    public UserServiceImplementation(UserPseudoDataBase userDb) {
        this.userDb = userDb;
    }

    @Override
    public List<User> getAllUsers() {
        return userDb.getAllUsers();
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userDb.findUserById(id);
    }

    @Override
    public User saveUser(User user) {
        return userDb.saveUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDb.updateUser(user);
    }

    @Override
    public boolean deleteUser(String id) {
        return userDb.deleteUser(id);
    }
}
