package com.flight.qrpinger.controller;

import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.exceptions.AlreadyAUserException;
import com.flight.qrpinger.exceptions.UserNotFoundException;
import com.flight.qrpinger.repository.UserRepository;
import com.flight.qrpinger.service.email.EmailService;
import com.flight.qrpinger.service.qrgen.QRService;
import com.flight.qrpinger.service.sms.TextService;
import com.google.zxing.WriterException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final QRService qrService;
    private final EmailService emailService;
    private final TextService textService;
    private final UserRepository userRepository;
    private final Logger logger;


    public UserController(QRService qrService, EmailService emailService, UserRepository userRepository,
                          TextService textService) {
        this.textService = textService;
        this.qrService = qrService;
        this.emailService = emailService;
        this.userRepository = userRepository;
        logger = LogManager.getLogger(UserController.class);
    }

    @PostMapping("/signup")
    public ResponseEntity newUser(@RequestBody User user) {
        User containedUser = userRepository.findByEmail(user.getEmail());
        if(containedUser != null) {
            logger.log(Level.ERROR, "User [" + user + "] already exists. Throwing new AlreadyAUserException...");
            throw new AlreadyAUserException("User with that email already exists");
        }
        else {
            ResponseEntity response = ResponseEntity.ok(userRepository.save(user));
            try {
                QRCode qrCode = qrService.generate(user.getId());
                emailService.sendEmail(user.getEmail(), "QRPinger - Your QR Code", "Enjoy!", qrCode.toFile());
            } catch (WriterException | MessagingException | IOException e) {
                //TODO - Handle these errors
            }
            return response;
        }
    }

    @GetMapping("/{id}")
    ResponseEntity pingUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()){
            logger.log(Level.ERROR, "No user with id equal to [" + id + "] Throwing UserNotFoundException...");
            throw new UserNotFoundException("No user with that id exists...");
        }

        textService.sendText(userOptional.get().getPhoneNumber());

        return ResponseEntity.ok(userOptional.get());
    }

}
