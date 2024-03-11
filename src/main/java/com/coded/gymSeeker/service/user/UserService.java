package com.coded.gymSeeker.service.user;

import com.coded.gymSeeker.bo.member.CreateUserDetails;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    List<String> getALlUsersWithStrongPassword();

    void saveUser(CreateUserDetails createUserDetails);

    User getUserById(Long userId);
}




