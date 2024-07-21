package com.example.p.dao;

import com.example.p.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Integer> {

}