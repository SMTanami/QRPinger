package com.flight.qrpinger.domain;

import com.flight.qrpinger.service.qrgen.QRServiceImpl;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QRCodeTest {

    private static QRCode qrCode;

    @BeforeAll
    static void setUp() throws WriterException {
        QRServiceImpl qrService = new QRServiceImpl();

        User user = new User(18L, "John", "Doe", "+1555347555", "qrpinger@gmail.com");
        qrCode = qrService.generate(user);
    }

    @Test
    @Order(1)
    void toFile() throws IOException {
        assertTrue(qrCode.toFile().exists());
    }

    @Test
    @Order(2)
    void deleteFile() throws IOException {
        assertTrue(qrCode.deleteFile());
    }
}