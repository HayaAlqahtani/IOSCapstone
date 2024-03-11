package com.coded.gymSeeker.controller.auth;

import com.coded.gymSeeker.bo.auth.AuthenticationResponse;
import com.coded.gymSeeker.bo.auth.CreateLoginRequest;
import com.coded.gymSeeker.bo.auth.CreateSignUpRequest;
import com.coded.gymSeeker.bo.auth.LogOutResponce;
import com.coded.gymSeeker.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody CreateSignUpRequest createSignupRequest){
        authService.signup(createSignupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");

    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody CreateLoginRequest createLoginRequest){

        AuthenticationResponse responce = (AuthenticationResponse) authService.login(createLoginRequest);
        HttpStatus status = HttpStatus.OK;
        if (responce==null){
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responce, status);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutResponce logoutResponce){
        authService.logout(logoutResponce);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


