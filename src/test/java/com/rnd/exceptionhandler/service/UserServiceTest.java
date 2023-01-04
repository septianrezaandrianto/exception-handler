package com.rnd.exceptionhandler.service;

import com.rnd.exceptionhandler.dto.UserRequest;
import com.rnd.exceptionhandler.entity.User;
import com.rnd.exceptionhandler.exception.NotFoundException;
import com.rnd.exceptionhandler.generator.NameGenerator;
import com.rnd.exceptionhandler.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayNameGeneration(NameGenerator.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Test
    public void getUserList() {
        List<User> userList = userList();
        when(userRepository.findAll()).thenReturn(userList);

        List<User> service = userService.getUserList();
        assertEquals(2, service.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserList_empty() {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        List<User> service = userService.getUserList();
        assertEquals(0, service.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserByName() {
        when(userRepository.getUserByName("Reza")).thenReturn(getUser("Reza",
                "reza@gmail.com", "M", "123456789098",
                "Bekasi", 25));

        User service = userService.getUserByName("Reza");
        assertEquals("Reza", service.getName());
        verify(userRepository, times(2)).getUserByName("Reza");
    }

    @Test
    public void getUserByName_failed() {
        when(userRepository.getUserByName("Reza")).thenReturn(getUser("Reza",
                "reza@gmail.com", "M", "123456789098",
                "Bekasi", 25));

        assertThrows(NotFoundException.class, ()-> {
            userService.getUserByName("Rahmat");
        });


    }
    @Test
    public void createUser() {
        UserRequest userReq = getUserRequest("Reza",
                "reza@gmail.com", "M", "123456789098",
                "Bekasi", 25);
        User user = getUser(userReq.getName(), userReq.getEmail(), userReq.getGender(), userReq.getPhoneNumber(),
                userReq.getAddress(), userReq.getAge());
        userService.createUser(userReq);
        verify(userRepository, times(1)).save(user);
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
