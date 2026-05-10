package com.example.taskserver.api.controller;

import com.example.taskserver.api.dto.UserDto;
import com.example.taskserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/login")
    public UserDto login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        return userService.login(email, password);
    }

    @PostMapping("/registration")
    public UserDto registration(@RequestBody UserDto userDto){
        return userService.registration(userDto);
    }

    @DeleteMapping
    public void deleteUser(String id){
        userService.delete(id);
    }

}
