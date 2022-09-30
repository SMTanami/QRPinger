package com.flight.qrpinger.service.email;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    private final Logger logger;
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.logger = LogManager.getLogger(EmailServiceImpl.class);
    }
    @Override
    public void sendEmail(String to, String subject, String body, File qrCodeFile) throws MessagingException {
        MimeMessage message = createEmailMessage(to, subject, body, qrCodeFile);
        mailSender.send(message);
        logger.log(Level.INFO, "QR code emailed to " + to);
    }

    private MimeMessage createEmailMessage(String to, String subject, String body, File qrFilePath) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("QRPinger");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        helper.addAttachment("Your Code.jpg", qrFilePath);

        return msg;
    }
}
