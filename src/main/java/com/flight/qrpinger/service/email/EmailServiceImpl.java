package com.flight.qrpinger.service.email;

import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.exceptions.FailedToEmailException;
import lombok.extern.java.Log;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
@Log
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(User toUser, String subject, String body, QRCode qrCode) throws IOException, FailedToEmailException {
        try {
            MimeMessage message = createEmailMessage(toUser, subject, body, qrCode.toFile());
            mailSender.send(message);
            log.info("QR code emailed to " + toUser.getEmail());
            if (!qrCode.deleteFile()) log.warning("Filed to delete file at path: " + qrCode.getPath());
            else log.info("Successfully deleted QR file at path: " + qrCode.getPath());
        } catch (MessagingException e) {
            throw new FailedToEmailException("Could not email user...");
        }
    }

    private MimeMessage createEmailMessage(User user, String subject, String body, File qrFilePath) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("QRPinger");
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(body);
        helper.addAttachment("Your Code.jpg", qrFilePath);

        return msg;
    }
}
