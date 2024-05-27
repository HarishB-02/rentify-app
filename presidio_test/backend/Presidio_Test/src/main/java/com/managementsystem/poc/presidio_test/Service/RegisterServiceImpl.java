package com.managementsystem.poc.presidio_test.Service;

import com.managementsystem.poc.presidio_test.model.User;
import com.managementsystem.poc.presidio_test.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService{
    UserRepo userRepo;

    public RegisterServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Map<String,String> registerUser(User user) {
        Map<String,String> map = new HashMap<>();
        try{
             map = pageValidation(user);
            if(map.isEmpty()){
                User newUser = new User();
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setEmail(user.getEmail());
                newUser.setPassword(user.getPassword());
                newUser.setBuyer(user.isBuyer());
                newUser.setPhoneNumber(user.getPhoneNumber());
                newUser.setSeller(user.isSeller());
                userRepo.save(newUser);
                map = new HashMap<>();
                map.put("status", "success");
            } else {
                map.put("status", "error");
            }

        }catch ( Exception e){
            e.printStackTrace();
            map.put("status", "error");
        }
        return map;
    }



    public Map<String, String> pageValidation(User user){

        Map<String, String> map = new HashMap<String, String>();
        if(user.getFirstName()==null || user.getFirstName().isEmpty()){
            map.put("firstName","firstName should not be empty");
        }

        if( user.getLastName()==null || user.getLastName().isEmpty()){

            map.put("lastName","lastName should not be empty");
        }

        if(!user.isBuyer() && !user.isSeller()){
            map.put("BuyerOrSeller","User should be a buyer or a seller");
        }
        if(user.isBuyer() && user.isSeller()){
            map.put("BuyerOrSeller","User should not be a buyer and a seller at the same time");

        }
        if (user.getEmail()==null || user.getEmail().isEmpty()){
            map.put("email","email should not be empty");
        }
        if (userRepo.findByEmailId(user.getEmail())!=null){
            map.put("email","User already exists");
        }
        if (user.getPassword()==null || user.getPassword().isEmpty()){
            map.put("password","Password should not be empty");
        }
        if (user.getPhoneNumber()==null || user.getPhoneNumber().isEmpty()){
            map.put("phoneNumber","Phone number should not be empty");
        }

        return map;


    }


}
