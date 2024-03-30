package com.cobra.controllers;


import com.cobra.models.BasicMeasurements;
import com.cobra.models.User;
import com.cobra.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public UserDetails getUserByName(@PathVariable("userName") String userName) {
        return userService.loadUserByUsername(userName);
    }

    @PostMapping("/{username}/measurements")
    public User saveUsersMeasurements(@RequestBody List<BasicMeasurements> measurements){
        log.info("Save users measurements " + measurements);
        return userService.saveUsersMeasurements(measurements);
    }


}
