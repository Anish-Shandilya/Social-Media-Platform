package com.example.p.dao;

import com.example.p.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDAO extends JpaRepository<Post, Integer> {
    List<Post> findAllByOrderByDateDesc();
}
