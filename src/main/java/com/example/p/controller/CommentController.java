package com.example.p.controller;

import com.example.p.dao.CommentDAO;
import com.example.p.error.UserError;
import com.example.p.model.Comment;
import com.example.p.model.CommentDTO;
import com.example.p.model.Post;
import com.example.p.model.UserClass;
import com.example.p.service.CommentService;
import com.example.p.service.PostService;
import com.example.p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.p.dao.*;

import java.util.Optional;

@RestController
@RequestMapping
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userservice;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;


    @PostMapping("/comment")
    public ResponseEntity<Object> createComment(@RequestBody CommentDTO commentDTO) {


        //System.out.println(commentDTO.getUserId());
        if (commentDTO.getPostID() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserError("Post does not exist"));
        }

        if (commentDTO.getUserID() == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserError("User does not exist"));
        }

        Optional<Post> p = postService.getPost(commentDTO.getPostID());

        Optional<UserClass> u = userservice.findById(commentDTO.getUserID());

        if(u.isPresent()){
            if(p.isPresent()){
                commentService.createComment(commentDTO.getCommentBody(), commentDTO.getPostID(), commentDTO.getUserID());
                return ResponseEntity.ok("Comment created successfully");
            }

            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserError("Post does not exist"));
        }

        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserError("User does not exist"));


    }

    @GetMapping("/comment")
    public ResponseEntity<?> getComment(@RequestParam Integer commentID) {

        Optional<Comment> c = commentService.getComment(commentID);

        if(c.isPresent()){

            return ResponseEntity.ok(c.get());
        }

        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserError("Comment does not exist"));


    }

    @PatchMapping("/comment")
    public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO) {

        UserError userError = new UserError("Comment does not exist");
        Optional<Comment> c = commentService.getComment(commentDTO.getPostID());

        if(c.isPresent()){

            //postService.deletePost(postID);

            commentService.updateComment(commentDTO.getPostID(), commentDTO.getCommentBody());

            //p.get().setPostBody(postDTO.getPostBody());


            return ResponseEntity.ok("Comment edited successfully");
        }

        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);

    }

    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestParam Integer commentID) {
        UserError userError = new UserError("Comment does not exist");
        Optional<Comment> c = commentService.getComment(commentID);

        if(c.isPresent()){

            commentService.deleteComment(commentID);

//            postService.getPost(postID).ifPresent(
//
//
//            );
            return ResponseEntity.ok("Comment deleted");
        }

        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);

    }
}
