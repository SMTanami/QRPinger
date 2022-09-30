package com.flight.qrpinger.service.email;

import javax.mail.MessagingException;
import java.io.File;

public interface EmailService {
    void sendEmail(String to, String subject, String body, File qrCodeFile) throws MessagingException;
}
