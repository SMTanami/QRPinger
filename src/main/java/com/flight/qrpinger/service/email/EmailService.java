package com.flight.qrpinger.service.email;

import com.flight.qrpinger.domain.User;

import javax.mail.MessagingException;
import java.io.File;

public interface EmailService {
    void sendEmail(User user, String subject, String body, File qrCodeFile) throws MessagingException;
}
