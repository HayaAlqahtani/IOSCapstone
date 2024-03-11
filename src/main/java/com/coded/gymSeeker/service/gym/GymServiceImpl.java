package com.coded.gymSeeker.service.gym;

import com.coded.gymSeeker.bo.gym.CreateGymRequest;
import com.coded.gymSeeker.entity.GymEntity;
import com.coded.gymSeeker.reposatriy.GymRepository;
import com.coded.gymSeeker.util.enums.Gender;

import java.util.List;
import java.util.stream.Collectors;

public class GymServiceImpl implements GymService{


    private final GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public List<GymEntity> getAllGymsBasedOnFilter(Gender gender) {
        return gymRepository.findAll()
                .stream()
                .filter(nurse -> gender == null || nurse.getGender() == gender)
                .collect(Collectors.toList());
    }



    @Override
    public GymEntity getGymById(Long id) {
        return gymRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public void createGym(CreateGymRequest createGymRequest) {
        GymEntity gym = new GymEntity();
        gym.setName(createGymRequest.getName());
        gym.setWorkingHours(createGymRequest.getWorkingHours());
        gym.setLocation(createGymRequest.getLocation());
        gym.setGender(createGymRequest.getGender());
        gymRepository.save(gym);
    }
}

