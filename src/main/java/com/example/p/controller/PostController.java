package com.example.p.controller;

import com.example.p.error.UserError;
import com.example.p.model.Post;
import com.example.p.model.PostDTO;
import com.example.p.model.UserClass;
import com.example.p.service.PostService;
import com.example.p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userservice;

    @PostMapping("/post")
    public ResponseEntity<Object> createPost(@RequestBody PostDTO postDTO) {
        UserError userError = new UserError("User does not exist");
        Optional<UserClass> u = userservice.findById(postDTO.getUserID());

        if(u.isPresent()){
            postService.createPost(postDTO.getPostBody(), postDTO.getUserID());
            return ResponseEntity.ok("Post created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);
        }
    }

    @GetMapping("/post")
    public ResponseEntity<?> getPost(@RequestParam Integer postID) {
        UserError userError = new UserError("Post does not exist");

        Optional<Post> p = postService.getPost(postID);

        if(p.isPresent()){
            return ResponseEntity.ok(postService.getPost(postID));
        }

        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);

//        return postService.getPost(postID)
//                .map(post -> ResponseEntity.ok(post))
//                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/post")
    public ResponseEntity<?> deletePost(@RequestParam Integer postID) {
        UserError userError = new UserError("Post does not exist");
        Optional<Post> p = postService.getPost(postID);

        if(p.isPresent()){

            postService.deletePost(postID);

//            postService.getPost(postID).ifPresent(
//
//
//            );
            return ResponseEntity.ok("Post deleted");
        }

        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);

    }

    @PatchMapping("/post")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO) {

        UserError userError = new UserError("Post does not exist");
        Optional<Post> p = postService.getPost(postDTO.getPostID());

        if(p.isPresent()){

            //postService.deletePost(postID);

            postService.updatePost(postDTO.getPostID(), postDTO.getPostBody());

            //p.get().setPostBody(postDTO.getPostBody());


            return ResponseEntity.ok("Post edited successfully");
        }

        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);

//        return postService.updatePost(postDTO.getPostID(), postDTO.getPostBody())
//                .map(post -> ResponseEntity.ok("Post edited successfully"))
//                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPosts() {
        List<Post> l = postService.getAllPosts();

        Collections.reverse(l);

        return ResponseEntity.ok(l);
    }
}
