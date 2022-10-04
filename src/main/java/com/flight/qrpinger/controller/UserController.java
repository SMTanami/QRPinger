package com.flight.qrpinger.controller;

import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.exceptions.AlreadyAUserException;
import com.flight.qrpinger.service.email.EmailService;
import com.flight.qrpinger.service.qrgen.QRService;
import com.flight.qrpinger.service.sms.TextService;
import com.flight.qrpinger.service.user.UserService;
import com.google.zxing.WriterException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@Log
@RestController
@RequestMapping("/user")
public class UserController {

    private final QRService qrService;
    private final EmailService emailService;
    private final TextService textService;
    private final UserService userService;


    public UserController(QRService qrService, EmailService emailService, UserService userService,
                          TextService textService) {
        this.textService = textService;
        this.qrService = qrService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity newUser(@RequestBody User user) {

        try {
            User savedUser = userService.saveUser(user);
            QRCode qrCode = qrService.generate(user);
            emailService.sendEmail(user, "QRPinger - Your QR Code", "Enjoy!", qrCode);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (WriterException | IOException | MessagingException e) {
            log.severe("newUser Exception "+e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        } catch (AlreadyAUserException e) {
            log.warning("User already exists. " + user);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict saving: " + user);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something bad happened. "+e.toString());
        }
    }

    @GetMapping("/{id}")
    ResponseEntity pingUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        textService.sendText(user);
        return ResponseEntity.ok(user);
    }

}
