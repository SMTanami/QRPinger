package com.flight.qrpinger.service.sms;

import com.flight.qrpinger.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TextServiceImplTest {

    @Autowired
    private TextServiceImpl textService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendText() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+12294539202")
                .build();
        textService.sendText(user);
    }
}