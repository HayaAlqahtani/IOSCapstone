package com.coded.gymSeeker.service.auth;

import com.coded.gymSeeker.bo.customUserDetails.CustomUserDetails;
import com.coded.gymSeeker.entity.UserEntity;
import com.coded.gymSeeker.reposatriy.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
        private final UserRepository userRepository;

        public CustomUserDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }


      @Override
       public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            try {
                return buildCustomUserDetailsOfUsername(s);
            } catch (UsernameNotFoundException e) {
              throw new RuntimeException(e);
         }
       }

        private CustomUserDetails buildCustomUserDetailsOfUsername(String username) throws UsernameNotFoundException {
            UserEntity user=userRepository.findByUsername(username)
                    .orElseThrow();
            if (user == null){
                throw new UsernameNotFoundException("User not found");
            }
            CustomUserDetails userDetails = new CustomUserDetails();
            userDetails.setId(user.getId());
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getPassword());
            userDetails.setRole(user.getRoles().getTitle().name());

            return userDetails;
        }
    }


