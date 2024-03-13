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
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody CreateSignUpRequest createSignupRequest) {
            AuthenticationResponse response = authService.signup(createSignupRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody CreateLoginRequest createLoginRequest){
        AuthenticationResponse response=authService.login(createLoginRequest);
        HttpStatus status= HttpStatus.OK;
        if (response == null){
            status= HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(response, status);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutResponce logoutResponce){
        authService.logout(logoutResponce);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


