package com.flight.qrpinger.service.email;

import com.flight.qrpinger.domain.QRCode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    public void sendEmail(String to, String subject, String body, QRCode qrCode) {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom("QRPinger");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            helper.addAttachment(qrCode.getFileName(), new File(qrCode.getFilepath()));
            mailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setFrom("qrpinger@gmail.com");
//        message.setSubject(subject);
//        message.setText(body);
//        mailSender.send(message);
    }
}
