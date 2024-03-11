package com.coded.gymSeeker.service.gym;

import com.coded.gymSeeker.bo.gym.CreateGymRequest;
import com.coded.gymSeeker.entity.GymEntity;
import com.coded.gymSeeker.util.enums.Gender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GymService {
    List<GymEntity> getAllGymsBasedOnFilter(Gender gender);

    GymEntity getGymById(Long id);
    void createGym(CreateGymRequest createGymRequest);
}


