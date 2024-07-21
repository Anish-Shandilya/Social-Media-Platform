package com.example.p.service;


import com.example.p.dao.UserDAO;
import com.example.p.model.UserClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserClass signUp(UserClass newUser){
        return userDAO.save(newUser);
    }

    public Optional<UserClass> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public List<UserClass> findAllUsers() {
        return userDAO.findAll();
    }

    public Optional<UserClass> findById(Integer userID) {
        return userDAO.findById(userID);
    }

}
