package com.managementsystem.poc.presidio_test.controller;

import com.managementsystem.poc.presidio_test.Service.RegisterService;
import com.managementsystem.poc.presidio_test.model.User;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("register")
public class RegisterController {
    RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public Map<String, String> registerUser(@RequestBody User user) {

        return registerService.registerUser(user);

    }
}
