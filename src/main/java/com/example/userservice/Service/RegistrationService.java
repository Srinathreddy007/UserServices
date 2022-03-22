package com.example.userservice.Service;

import com.example.userservice.Repository.UserRepo;
import com.example.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService
{
    @Autowired
    UserRepo userRepo;
    public User saveUser(User user)
    {

        return userRepo.save(user);

    }
    public User fetchUserbyEmail(String email)
    {
        return userRepo.findByEmail(email);

    }
    public User fetchUserEmailandPassword(String email,String password)
    {
        return userRepo.findByEmailAndPassword(email,password);


    }

//    public List<User> findusers() {
//        return userRepo.findAll();
//   }
}
