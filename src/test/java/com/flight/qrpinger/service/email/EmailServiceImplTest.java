package com.flight.qrpinger.service.email;

import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.exceptions.FailedToEmailException;
import com.flight.qrpinger.service.qrgen.QRServiceImpl;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EmailServiceImplTest {
    @Autowired
    private QRServiceImpl qrService;

    @Autowired
    private EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void gotService() {
        org.junit.jupiter.api.Assertions.assertNotNull(emailService);
    }

    @Test
    void sendEmail() {
        assertDoesNotThrow(() -> {
            var user = User.builder().email("bogus@bogus.bogus").build();
            var qrCode = qrService.generate(user);

            emailService.sendEmail(user, "test", "test body", qrCode);
        });
    }

}