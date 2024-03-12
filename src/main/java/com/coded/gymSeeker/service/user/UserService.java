package com.coded.gymSeeker.service.user;

import com.coded.gymSeeker.bo.member.CreateUserDetails;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    List<String> getALlUsersWithStrongPassword();

    void saveUser(CreateUserDetails createUserDetails);

    User getUserById(Long userId);
}




