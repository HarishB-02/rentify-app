package com.managementsystem.poc.presidio_test.Service;

import com.managementsystem.poc.presidio_test.model.User;

import java.util.Map;

public interface RegisterService {
    public Map<String,String> registerUser(User user);
}
