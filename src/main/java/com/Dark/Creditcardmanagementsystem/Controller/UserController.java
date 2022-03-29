package com.Dark.Creditcardmanagementsystem.Controller;

import com.Dark.Creditcardmanagementsystem.Service.UserService;
import com.Dark.Creditcardmanagementsystem.model.Request.UserRequest;
import com.Dark.Creditcardmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
     private UserService userService;

     @Autowired
     public UserController(UserService userService){
         this.userService=userService;
     }
     @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody UserRequest user){
         User savedUser=userService.addUser(user);
         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
       }

       @GetMapping("/user")
       public ResponseEntity<List<User>> getAllUsers(){
         return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
       }

}
