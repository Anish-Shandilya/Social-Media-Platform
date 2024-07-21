package com.example.p.service;

import com.example.p.dao.CommentDAO;
import com.example.p.dao.PostDAO;
import com.example.p.dao.UserDAO;
import com.example.p.model.Comment;
import com.example.p.model.Post;
import com.example.p.model.UserClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentDAO commentRepository;
    @Autowired
    private PostDAO postRepository;
    @Autowired
    private UserDAO userDAO;

    public Optional<Comment> createComment(String commentBody, Integer postId, Integer userId) {
        Optional<Post> post = postRepository.findById(postId);
        Optional<UserClass> user = userDAO.findById(userId);

        if (post.isPresent() ) {
            if(user.isPresent()){

                return Optional.of(commentRepository.save(new Comment(commentBody, post.get(), user.get())));}
            else return Optional.empty();
        } else
            return Optional.empty();

    }

    public Optional<Comment> getComment(Integer commentId) {
        return commentRepository.findById(commentId);
    }

    public Optional<Comment> updateComment(Integer commentId, String newCommentBody) {
        return commentRepository.findById(commentId)
                .map(comment -> {
                    comment.setCommentBody(newCommentBody);
                    return commentRepository.save(comment);
                });
    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
}
