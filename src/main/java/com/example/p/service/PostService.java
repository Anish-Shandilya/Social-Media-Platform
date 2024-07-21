package com.example.p.service;

import com.example.p.dao.PostDAO;

import com.example.p.dao.UserDAO;
import com.example.p.model.Post;
import com.example.p.model.UserClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostDAO postRepository;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    UserService userservice;

    public Optional<Post> createPost(String postBody, Integer userId) {

        Optional<UserClass> u = userservice.findById(userId);


        return userDAO.findById(userId)
                .map(user -> postRepository.save(new Post(postBody,u.get())));
    }

    public Optional<Post> getPost(Integer postId) {
        return postRepository.findById(postId);
    }

    public Optional<Post> updatePost(Integer postId, String newPostBody) {
        return postRepository.findById(postId)
                .map(post -> {
                    post.setPostBody(newPostBody);
                    return postRepository.save(post);
                });
    }

    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByDateDesc();
    }
}
