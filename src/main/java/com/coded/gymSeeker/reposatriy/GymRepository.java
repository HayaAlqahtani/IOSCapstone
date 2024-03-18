package com.coded.gymSeeker.reposatriy;

import com.coded.gymSeeker.entity.GymEntity;
import com.coded.gymSeeker.util.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymRepository extends JpaRepository<GymEntity,Long> {
    @Query(value = "SELECT * FROM gym r where r.gender = :gender",nativeQuery = true)
    List<GymEntity> findByGender(Gender gender);
     //List<GymEntity>findallgyms();
}


