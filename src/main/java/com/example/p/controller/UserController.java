package com.example.p.controller;

import com.example.p.error.UserError;
import com.example.p.model.UserClass;
import com.example.p.model.UserClass2;
import com.example.p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userservice;



    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserClass user) {
        if (userservice.findByEmail(user.getEmail()).isPresent()) {

            UserError userError = new UserError("Forbidden, Account already exists");

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED).body(userError);
        }
        userservice.signUp(user);
        return ResponseEntity.ok("Account Creation Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserClass user){

        UserError userError = new UserError("User does not exist");

        return userservice.findByEmail(user.getEmail())
                .map(user1 -> {
                    if (user.getPassword().equals(user1.getPassword())) {
                        return ResponseEntity.ok("Login Successful");
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserError("Username/Password Incorrect"));
                    }
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError));
    }


    @GetMapping("/user")
    public ResponseEntity<Object> getUserDetail(@RequestParam Integer userID) {

        UserError userError = new UserError("User does not exist");

        Optional<UserClass> u = userservice.findById(userID);

        if(u.isPresent()) {

            UserClass2 u2 = new UserClass2(u.get().getName(), u.get().getId(),u.get().getEmail());

            return ResponseEntity.ok(u2);

        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);


    }


    @GetMapping("/users")
    public List<UserClass2> getAllUsers() {

        List<UserClass> user1 = userservice.findAllUsers();




        List<UserClass2> user2 = new ArrayList<>();

        for(UserClass u : user1){
            user2.add(new UserClass2(u.getName(), u.getId(),u.getEmail()));
        }

        return user2;
    }

}
