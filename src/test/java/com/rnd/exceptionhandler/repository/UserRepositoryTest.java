package com.rnd.exceptionhandler.repository;

import com.rnd.exceptionhandler.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("should return success getUserByName")
    public void getUserByName() {
        when(userRepository.getUserByName("Reza"))
                .thenReturn(getUser("Reza", "reza@gmail.com", "M", "123456789098",
                        "Bekasi", 25));
        User user = getUser("Reza", "reza@gmail.com", "M", "123456789098",
                "Bekasi", 25);
        assertNotNull(user);
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
}
