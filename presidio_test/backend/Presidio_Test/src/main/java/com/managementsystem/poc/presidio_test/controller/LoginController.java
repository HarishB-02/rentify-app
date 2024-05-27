package com.managementsystem.poc.presidio_test.controller;

import com.managementsystem.poc.presidio_test.Service.LoginService;
import com.managementsystem.poc.presidio_test.model.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping
    public UserWrapper login(@RequestParam String email, @RequestParam String password ) {
        return loginService.login(email,password);
    }
}
