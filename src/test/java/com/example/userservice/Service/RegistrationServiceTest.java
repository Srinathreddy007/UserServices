package com.example.userservice.Service;


import com.example.userservice.Repository.UserRepo;
import com.example.userservice.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@WebMvcTest(RegistrationService.class)
@SpringBootTest(classes={RegistrationServiceTest.class})
public class RegistrationServiceTest
{
    @Mock
    UserRepo userRepo;
    @InjectMocks
    RegistrationService regServ;



    @Test
    void saveUser_success() {
        User user=new User(1L,"Ram","Kumar","ram@gmail.com","test","male");
       //when and then mocking
        when(userRepo.save(user)).thenReturn(user);
        //validation
        assertEquals(user,regServ.saveUser(user));
    }
    @Test
    void saveUser_failure() {
        User user=new User(1L,"Ram","Kumar","ram@gmail.com","test","male");
        //when and then mocking
        User user1=new User(2L,"kamal","Kumar","kamal@gmail.com","test","male");
        when(userRepo.save(user)).thenReturn(user);
        //validation
        assertNotEquals(user1,regServ.saveUser(user));
    }

    @Test
    void fetchUserbyEmail_success() {
        User user=new User(1L,"Ram","Kumar","ram@gmail.com","test","male");
        when(userRepo.findByEmail("ram@gmail.com")).thenReturn(user);
        assertEquals(user,regServ.fetchUserbyEmail("ram@gmail.com"));
    }
    @Test
    void fetchUserbyEmail_failure()
    {
        User user=new User(1L,"Ram","Kumar","ram@gmail.com","test","male");
        User user1=new User(2L,"kamal","Kumar","kamal@gmail.com","test","male");

        when(userRepo.findByEmail("ram@gmail.com")).thenReturn(user);
        assertNotEquals(user,regServ.fetchUserbyEmail("kamal@gmail.com"));
    }

    @Test
    void fetchUserEmailandPassword_success()
    {
        User user=new User(1L,"Ram","Kumar","ram@gmail.com","test","male");
        when(userRepo.findByEmailAndPassword("ram@gmail.com","test")).thenReturn(user);
        assertEquals(user,regServ.fetchUserEmailandPassword("ram@gmail.com","test"));
    }
    @Test
    void fetchUserEmailandPassword_failure()
    {
        User user=new User(1L,"Ram","Kumar","ram@gmail.com","test","male");
        User user1=new User(2L,"kamal","Kumar","kamal@gmail.com","test","male");

        when(userRepo.findByEmailAndPassword("ram@gmail.com","test")).thenReturn(user);
        assertNotEquals(user,regServ.fetchUserEmailandPassword("kamal@gmail.com","test"));
    }
}
