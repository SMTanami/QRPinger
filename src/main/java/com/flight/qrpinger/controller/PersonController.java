package com.flight.qrpinger.controller;

import com.flight.qrpinger.domain.Person;
import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.exceptions.UserNotFoundException;
import com.flight.qrpinger.repository.PersonRepository;
import com.flight.qrpinger.service.email.EmailService;
import com.flight.qrpinger.service.qrgen.QRService;
import com.flight.qrpinger.service.sms.TextService;
import com.google.zxing.WriterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class PersonController {

    private final QRService qrService;
    private final EmailService emailService;
    private final TextService textService;
    private final PersonRepository personRepository;


    public PersonController(QRService qrService, EmailService emailService, PersonRepository personRepository,
                            TextService textService) {
        this.textService = textService;
        this.qrService = qrService;
        this.emailService = emailService;
        this.personRepository = personRepository;
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(personRepository.findAll());
    }

    @PostMapping("/signup")
    public ResponseEntity newUser(@RequestBody Person newPerson) {
        ResponseEntity response = ResponseEntity.ok(personRepository.save(newPerson));
        try {
            QRCode qrCode = qrService.generate(newPerson.getId(), newPerson.getLastName());
            emailService.send(newPerson.getEmail(), "QRPinger - Your QR Code", "Enjoy!", qrCode);
        } catch (IOException | WriterException e) {
            //Log here?
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/{id}")
    ResponseEntity getOneUser(@PathVariable Long id) {
        if(personRepository.existsById(id)) {
            textService.sendText(personRepository.findById(id).get().getPhoneNumber());
        }
        return ResponseEntity.ok(personRepository.findById(id).orElseThrow(() -> new UserNotFoundException()));
    }

}
