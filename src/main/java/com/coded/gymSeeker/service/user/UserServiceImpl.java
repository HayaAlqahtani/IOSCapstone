package com.coded.gymSeeker.service.user;

import com.coded.gymSeeker.bo.member.CreateUserDetails;
import com.coded.gymSeeker.entity.UserEntity;
import com.coded.gymSeeker.reposatriy.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(CreateUserDetails createUserDetails) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserDetails.getUsername());
        userEntity.setEmail(createUserDetails.getEmail());
        userEntity.setGender(createUserDetails.getGender());
        userEntity.setPhoneNumber(createUserDetails.getPhoneNumber());
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public List<String> getALlUsersWithStrongPassword() {
        return userRepository.findAll().stream()
                .filter(user -> user.getPassword().length() > 8)
                .map(UserEntity::getUsername)
                .collect(Collectors.toList());
    }

}

