package com.flight.qrpinger.service.qrgen;

import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.domain.User;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QRServiceImplTest {

    @Autowired
    private QRServiceImpl qrService;

    @BeforeEach
    void setUp() {
        this.qrService = new QRServiceImpl();
    }

    @Test
    void generate() throws WriterException {
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "+1555247555";
        String email = "qrpinger@gmail.com";

        User expectedUser = new User(18L, firstName, lastName, phoneNumber, email);
        Path expectedPath = Path.of(System.getProperty("java.io.tmpdir") + expectedUser.getId());

        QRCode actual = qrService.generate(expectedUser);

        assertEquals(actual.getUser(), expectedUser);
        assertEquals(actual.getPath().toString(), expectedPath.toString());
        assertNotNull(actual.getMatrix());
    }
}