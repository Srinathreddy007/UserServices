package com.example.userservice.Controller;

import com.example.userservice.Repository.UserRepo;
import com.example.userservice.Service.RegistrationService;
import com.example.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("http://localhost:3000")
public class UserController
{
    @Autowired
    RegistrationService regServ;
    @Autowired
    UserRepo userRepo;
    @GetMapping("register")
    List<User> getusers()
    {
        return userRepo.findAll();
    }

    @PostMapping("register")
    public User registerUser(@RequestBody User user) throws Exception
    {
        String tempEmail=user.getEmail();
//        User userObj=null;
        if(tempEmail!=null && !"".equals(tempEmail))
        {
            User userobj= regServ.fetchUserbyEmail(tempEmail);
                    if(userobj!=null)
                    {
                        throw new Exception("user with "+tempEmail+"already exists");
                    }
        }
        User userObj=null;
        userObj=regServ.saveUser(user);
        return userObj;
    }
    @PostMapping("login")
    public Long Login(@RequestBody User user) throws Exception
    {
        String tempEmail=user.getEmail();
        String tempPass=user.getPassword();
        User userobj=null;
        if(tempEmail!=null&&tempPass!=null)
        {
            userobj=regServ.fetchUserEmailandPassword(tempEmail,tempPass);
        }

        return userobj.getUserId();

    }

}
