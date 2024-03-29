package com.coded.gymSeeker.service.auth;

import com.coded.gymSeeker.bo.auth.AuthenticationResponse;
import com.coded.gymSeeker.bo.auth.CreateLoginRequest;
import com.coded.gymSeeker.bo.auth.CreateSignUpRequest;
import com.coded.gymSeeker.bo.auth.LogOutResponce;
import com.coded.gymSeeker.bo.customUserDetails.CustomUserDetails;
import com.coded.gymSeeker.config.JWTUtil;
import com.coded.gymSeeker.entity.RoleEntity;
import com.coded.gymSeeker.entity.UserEntity;
import com.coded.gymSeeker.reposatriy.RoleRepository;
import com.coded.gymSeeker.reposatriy.UserRepository;
import com.coded.gymSeeker.util.BodyGuardException;
import com.coded.gymSeeker.util.UserNotFoundException;
import com.coded.gymSeeker.util.enums.Roles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final JWTUtil jwtUtil;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JWTUtil jwtUtil, RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AuthenticationResponse login(CreateLoginRequest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(), "username");
        requiredNonNull(createLoginRequest.getPassword(), "password");

        String username = createLoginRequest.getUsername().toLowerCase();

        String password = createLoginRequest.getPassword();

        authentication(username, password);
        System.out.println("line 51");

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);
        System.out.println("line 52");

        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer " + accessToken);
        return response;
    }

//    @Override
//    public AuthenticationResponse signup(CreateSignUpRequest createSignupRequest) {
//        RoleEntity roleEntity = roleRepository.findRoleEntityByTitle(Roles.user.name())
//                .orElseThrow(() -> new BodyGuardException("no Roles Found"));
//
//        UserEntity user = new UserEntity();
//        user.setUsername(createSignupRequest.getUsername());
//        user.setRoles(roleEntity);
//        user.setUsername(createSignupRequest.getUsername());
//        user.setPhoneNumber(createSignupRequest.getPhoneNumber());
//        user.setGender(createSignupRequest.getGender());
//        user.setEmail(createSignupRequest.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
//        userRepository.save(user);
//    }
@Override
public AuthenticationResponse signup(CreateSignUpRequest createSignupRequest) {
    RoleEntity roleEntity = roleRepository.findRoleEntityByTitle(Roles.user.name())
            .orElseThrow(() -> new BodyGuardException("no Roles Found"));

    UserEntity user = new UserEntity();
    user.setUsername(createSignupRequest.getUsername());
    user.setRoles(roleEntity);
    user.setUsername(createSignupRequest.getUsername().toLowerCase());
    user.setPhoneNumber(createSignupRequest.getPhoneNumber());
    user.setGender(createSignupRequest.getGender());
    user.setEmail(createSignupRequest.getEmail());
    user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
    userRepository.save(user);

    CustomUserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
    String accessToken = jwtUtil.generateToken(userDetails);

    AuthenticationResponse response = new AuthenticationResponse();
    response.setId(userDetails.getId());
    response.setUsername(userDetails.getUsername());
    response.setRole(userDetails.getRole());
    response.setToken("Bearer " + accessToken);

    return response;
}

    @Override
    public void logout(LogOutResponce logoutResponse) {
        requiredNonNull(logoutResponse.getToken(), "token");
    }

    private void requiredNonNull(Object obj, String name) {
        if (obj == null || obj.toString().isEmpty()) {
            throw new BodyGuardException(name + " can't be empty");
        }
    }

    private void authentication(String username, String password) {
        try {
         //   System.out.println("line 100 username and password: " +  username + password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("line 101 here ");

        } catch (BodyGuardException e) {
            throw new BodyGuardException("Incorrect password");
        } catch (AuthenticationServiceException e) {
            throw new UserNotFoundException("Incorrect username");
        }
    }

}