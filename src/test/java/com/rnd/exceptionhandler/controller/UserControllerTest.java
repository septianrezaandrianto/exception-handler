package com.rnd.exceptionhandler.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rnd.exceptionhandler.dto.UserRequest;
import com.rnd.exceptionhandler.entity.User;
import com.rnd.exceptionhandler.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should return success getUserByName")
    public void getUserList() throws Exception {
        when(userService.getUserList()).thenReturn(userList());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/getUserList")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @DisplayName("should return success getUserByName")
    public void getUserByName() throws Exception {
        when(userService.getUserByName("Reza")).thenReturn(getUser("Reza", "reza@gmail.com", "M", "123456789098",
                "Bekasi", 25));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/getUserByName/Reza")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @DisplayName("should return success createUser")
    public void createUser() throws Exception {
        when(userService.createUser(getUserRequest("Reza", "reza@gmail.com", "M", "123456789098",
                "Bekasi", 25)))
                .thenReturn(getUser("Reza", "reza@gmail.com", "M", "123456789098",
                        "Bekasi", 25));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(getUserRequest("Reza", "reza@gmail.com", "M", "123456789098",
                                "Bekasi", 25))))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content, "");
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private List<User> userList() {
        List<User> dataList = new ArrayList<>();
        dataList.add(getUser("Reza", "reza@gmail.com", "M", "123456789098",
                "Bekasi", 25));
        dataList.add(getUser("Septian", "septian@gmail.com", "M", "123456789088",
                "Jakarta", 30));
        return dataList;
    }


    private User getUser(String name,
                         String email,
                         String gender,
                         String phoneNUmber,
                         String address,
                         Integer age) {
        return  User.builder()
                .name(name)
                .age(age)
                .email(email)
                .gender(gender)
                .phoneNumber(phoneNUmber)
                .address(address)
                .build();
    }


    private UserRequest getUserRequest(String name,
                                       String email,
                                       String gender,
                                       String phoneNUmber,
                                       String address,
                                       Integer age) {
        return  UserRequest.builder()
                .name(name)
                .age(age)
                .email(email)
                .gender(gender)
                .phoneNumber(phoneNUmber)
                .address(address)
                .build();
    }
}
