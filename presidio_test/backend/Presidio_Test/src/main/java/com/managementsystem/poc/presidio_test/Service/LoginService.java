package com.managementsystem.poc.presidio_test.Service;

import com.managementsystem.poc.presidio_test.model.UserWrapper;

public interface LoginService {

    UserWrapper login(String email, String password);
}
