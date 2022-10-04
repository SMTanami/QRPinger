package com.flight.qrpinger.service.email;

import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.domain.User;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    void sendEmail(User toUser, String subject, String body, QRCode qrCode) throws MessagingException, IOException;
}
