package com.managementsystem.poc.presidio_test.Service;

import com.managementsystem.poc.presidio_test.model.User;
import com.managementsystem.poc.presidio_test.model.UserWrapper;
import com.managementsystem.poc.presidio_test.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    UserRepo userRepo;

    public LoginServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserWrapper login(String email, String password) {
        UserWrapper userWrapper = new UserWrapper();
        User user = userRepo.findByEmailId(email);
        if(user==null){
            userWrapper.setError("Login Failed");
            return userWrapper;
        }
        if(user.getPassword().equals(password)){
            userWrapper.setId(user.getId());
            userWrapper.setEmail(user.getEmail());
            userWrapper.setBuyer(user.isBuyer());
            userWrapper.setSeller(user.isSeller());
            userWrapper.setFirstName(user.getFirstName());
            userWrapper.setLastName(user.getLastName());
            userWrapper.setPhoneNumber(user.getPhoneNumber());
            return userWrapper;
        }
        userWrapper.setError("Login Failed");
        return userWrapper;
    }
}
