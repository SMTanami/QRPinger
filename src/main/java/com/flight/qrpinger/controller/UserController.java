package com.flight.qrpinger.controller;

import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.service.email.EmailService;
import com.flight.qrpinger.service.qrgen.QRService;
import com.flight.qrpinger.service.sms.TextService;
import com.flight.qrpinger.service.user.UserService;
import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

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
        return saveAndRespond(user);
    }

    @PostMapping("/signup/")
    public ResponseEntity newUserParams(@RequestParam(value = "firstName") String firstName,
                                        @RequestParam(value = "lastName") String lastName,
                                        @RequestParam(value = "phone") String phoneNumber,
                                        @RequestParam(value = "email") String email) {
        return saveAndRespond(User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .email(email).build());
    }

    @GetMapping("/{id}")
    ResponseEntity pingUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        textService.sendText(user);

        return ResponseEntity.ok(user);
    }

    private ResponseEntity saveAndRespond(User user) {
        User savedUser = userService.saveUser(user);
        try {
            QRCode qrCode = qrService.generate(user);
            emailService.sendEmail(user,"QRPinger - Your QR Code", "Enjoy!", qrCode);
        } catch (WriterException | IOException | MessagingException e) {
            //TODO - Handle these errors
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
