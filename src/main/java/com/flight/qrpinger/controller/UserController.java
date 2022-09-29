package com.flight.qrpinger.controller;

import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.exceptions.UserNotFoundException;
import com.flight.qrpinger.repository.UserRepository;
import com.flight.qrpinger.service.email.EmailService;
import com.flight.qrpinger.service.qrgen.QRService;
import com.flight.qrpinger.service.sms.TextService;
import com.google.zxing.WriterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final QRService qrService;
    private final EmailService emailService;
    private final TextService textService;
    private final UserRepository userRepository;


    public UserController(QRService qrService, EmailService emailService, UserRepository userRepository,
                          TextService textService) {
        this.textService = textService;
        this.qrService = qrService;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity newUser(@RequestBody User user) {
        ResponseEntity response = ResponseEntity.ok(userRepository.save(user));
        try {
            QRCode qrCode = qrService.generate(user.getId(), user.getLastName());
            userRepository.getReferenceById(user.getId()).setQrHash(qrCode.hashCode());
            emailService.sendEmail(user.getEmail(), "QRPinger - Your QR Code", "Enjoy!", qrCode);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/{id}")
    ResponseEntity getOneUser(@PathVariable Long id) {
        if(userRepository.existsById(id)) {
            textService.sendText(userRepository.findById(id).get().getPhoneNumber());
        }
        return ResponseEntity.ok(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException()));
    }

}
