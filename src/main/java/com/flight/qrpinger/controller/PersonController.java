package com.flight.qrpinger.controller;

import com.flight.qrpinger.domain.Person;
import com.flight.qrpinger.exceptions.UserNotFoundException;
import com.flight.qrpinger.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(personRepository.findAll());
    }

    @PostMapping("/signup")
    public ResponseEntity newUser(@RequestBody Person newPerson) {
        return ResponseEntity.ok(personRepository.save(newPerson));
    }

    @GetMapping("/{id}")
    ResponseEntity getOneUser(@PathVariable Long id) {
        return ResponseEntity.ok(personRepository.findById(id).orElseThrow(() -> new UserNotFoundException()));
    }

}
