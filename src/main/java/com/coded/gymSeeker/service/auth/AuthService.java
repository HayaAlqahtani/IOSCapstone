package com.coded.gymSeeker.service.auth;

import com.coded.gymSeeker.bo.auth.AuthenticationResponse;
import com.coded.gymSeeker.bo.auth.CreateLoginRequest;
import com.coded.gymSeeker.bo.auth.CreateSignUpRequest;
import com.coded.gymSeeker.bo.auth.LogOutResponce;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {


    AuthenticationResponse login(CreateLoginRequest createLoginRequest);


    void signup(CreateSignUpRequest createSignupRequest);


    void logout(LogOutResponce logoutResponse);
}
