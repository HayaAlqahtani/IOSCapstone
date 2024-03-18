package com.coded.gymSeeker.controller.gym;

import com.coded.gymSeeker.bo.gym.CreateGymRequest;
import com.coded.gymSeeker.bo.member.CreateUserDetails;
import com.coded.gymSeeker.entity.GymEntity;
import com.coded.gymSeeker.service.book.BookService;
import com.coded.gymSeeker.service.gym.GymService;
import com.coded.gymSeeker.service.user.UserService;
import com.coded.gymSeeker.util.enums.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gym")
public class GymController {
    private final GymService gymService;

    private final UserService userService;

    private final BookService bookService;

    public GymController(GymService gymService, UserService userService, BookService bookService) {
        this.gymService = gymService;
        this.userService = userService;
        this.bookService = bookService;
    }

    //create gym
    @PostMapping("admin/create")
    public ResponseEntity<String> createGym(@RequestBody CreateGymRequest createGymRequest) {
        gymService.createGym(createGymRequest);
        return ResponseEntity.ok("Gym created successfully");
    }


    @GetMapping("/filter")
    public List<GymEntity> getAllGyms(@RequestParam(required = false) Gender gender) {
        return gymService.getAllGymsBasedOnFilter(gender);
    }

    // Endpoint to fetch details of a specific gym by ID
    @GetMapping("/id")
    public GymEntity getGymById(@RequestParam Long id) {
        return gymService.getGymById(id);
    }
    @GetMapping("/get_all_gyms")
    public List<GymEntity> getAllGyms() {
        return gymService.getAllGyms();
    }
}



