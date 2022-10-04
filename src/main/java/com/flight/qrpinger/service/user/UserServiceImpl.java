package com.flight.qrpinger.service.user;

import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.exceptions.AlreadyAUserException;
import com.flight.qrpinger.exceptions.UserNotFoundException;
import com.flight.qrpinger.repository.UserRepository;
import lombok.extern.java.Log;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        User containedUser = userRepository.findByEmail(user.getEmail());
        if (containedUser != null) {
            log.severe("User [" + user + "] already exists. Throwing new AlreadyAUserException...");
            throw new AlreadyAUserException("User with that email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            log.severe("No user with id [" + id + "] Throwing UserNotFoundException...");
            throw new UserNotFoundException("No user with that id exists...");
        }

        return userOptional.get();
    }
}
