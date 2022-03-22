package com.example.userservice.Controller;

import com.example.userservice.Repository.UserRepo;
import com.example.userservice.Service.RegistrationService;
import com.example.userservice.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest()
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectmapper;
    @MockBean
    UserRepo userRepo;
    @MockBean
    RegistrationService regServ;


    @Test
    public void getusers_success() throws Exception
    {
        User user2=new User(1L,"Ram","Kumar","ram@gmail.com","test","male");
        //when and then mocking
        User user1=new User(2L,"kamal","Kumar","kamal@gmail.com","test","male");
        List<User> user =new ArrayList<>(Arrays.asList(user2,user1));
        Mockito.when(userRepo.findAll()).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/register").
                        contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userId", Matchers.is(2)));
        //.andExpect(jsonPath("$[1].firstName",is("nk")));



    }
    @Test
    public void getAllUsers_Failure() throws Exception
    {
        List<User> record1 =new ArrayList<>();
        Mockito.when(userRepo.findAll()).thenReturn(record1);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/p1/v1").
                        contentType(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createRegister_success() throws Exception {
        User user=new User(1L,"Ram","Kumar","sri@gmail.com","test","Male");

        Mockito.when(regServ.saveUser(Mockito.any(User.class))).thenReturn(user);
        //String content=objectWriter.writeValueAsString(records);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectmapper.writeValueAsString(user));

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Ram")));
    }
    @Test
    public void createLogin_success() throws Exception {
        User user=new User(1L,"Ram","Kumar","sri@gmail.com","test","Male");

        Mockito.when(regServ.fetchUserEmailandPassword(user.getEmail(), user.getPassword())).thenReturn(user);
        //String content=objectWriter.writeValueAsString(records);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectmapper.writeValueAsString(user));

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk());
    }
//    @Test//(expected=Exception.class)
//    public void createRegister_failure() throws Exception {
//        User user=new User(1L,"Ram","Kumar","sri@gmail.com","test","Male");
//
//        Mockito.when(regServ.fetchUserbyEmail(user.getEmail())).thenReturn(user);
//        //String content=objectWriter.writeValueAsString(records);
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.objectmapper.writeValueAsString(user));
//
//        mockMvc.perform(mockRequest)
//                .andDo(print())
//               // .andExpect(result ->
//              //          //assertThat("user with "+user.getEmail()+"already exists");
//                .andExpect(assertThrows("user with "+user.getEmail()+"already exists\""));
//    }


}