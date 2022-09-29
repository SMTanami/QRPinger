package com.flight.qrpinger.service.email;

import com.flight.qrpinger.domain.QRCode;

public interface EmailService {
    void sendEmail(String to, String subject, String body, QRCode qrCode);
}
