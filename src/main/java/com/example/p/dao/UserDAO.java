package com.example.p.dao;

import com.example.p.model.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<UserClass, Integer> {
    Optional<UserClass> findByEmail(String email);
//    Optional<UserClass> findByID(int id);
//    List<UserClass> findAll();
}
