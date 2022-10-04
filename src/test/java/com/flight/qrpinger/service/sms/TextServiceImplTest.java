package com.flight.qrpinger.service.sms;

import com.flight.qrpinger.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class TextServiceImplTest {

    private TextServiceImpl textService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        textService = new TextServiceImpl();
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