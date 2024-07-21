package com.example.p.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentID;
    private String commentBody;

    @JsonIgnore
    @ManyToOne
    private Post post;


    @ManyToOne
    private UserClass user;

    // Constructors, Getters, and Setters
//    public Comment() {
//    }

    public Comment(String commentBody, Post post, UserClass user) {
        this.commentBody = commentBody;
        this.post = post;
        this.user = user;
    }

    public Comment() {

    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public UserClass getUser() {
        return user;
    }

    public void setUser(UserClass user) {
        this.user = user;
    }
}
