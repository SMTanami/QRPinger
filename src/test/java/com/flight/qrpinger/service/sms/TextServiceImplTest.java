package com.flight.qrpinger.service.sms;

import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.service.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TextServiceImplTest {

    @Autowired
    private TextServiceImpl textService;

    @Autowired
    private UserService userService;

    private static User user;
    @BeforeAll
    static void setupAll() {
        user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+12294539202")
                .email("david@davidbharrison.com")
                .build();
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        textService = new TextServiceImpl();
        userService.saveUser(user);
    }

    @Test
    void sendText() {
        textService.sendText(user);
    }
}