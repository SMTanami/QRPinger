package com.flight.qrpinger.service.email;

import com.flight.qrpinger.domain.QRCode;

import java.awt.image.BufferedImage;

public interface EmailService {

    void send(String to, String subject, String body, QRCode qrCode);

}
