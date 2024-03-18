package com.coded.gymSeeker.controller.user;

import com.coded.gymSeeker.bo.member.CreateUserDetails;
import com.coded.gymSeeker.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {
    private final UserService userService; //injection this is the constructor with similar class name


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDetails createUserDetails) {
        try {
            userService.saveUser(createUserDetails);


        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(" ACTIVE or INACTIVE");
        }
        return ResponseEntity.ok(" User Has Been Created");
    }
    @GetMapping("/user-info")
    public ResponseEntity<CreateUserDetails> userinfo(@RequestParam Long userId){
        CreateUserDetails user = (CreateUserDetails) userService.getUserById(userId);
        if (user != null){
            return ResponseEntity.ok(user);

        }else {
            return ResponseEntity.notFound().build();
        }

    }
}
