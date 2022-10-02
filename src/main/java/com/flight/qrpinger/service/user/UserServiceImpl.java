package com.flight.qrpinger.service.user;

import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.exceptions.AlreadyAUserException;
import com.flight.qrpinger.exceptions.UserNotFoundException;
import com.flight.qrpinger.repository.UserRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        logger = LogManager.getLogger(UserServiceImpl.class);
    }

    @Override
    public User saveUser(User user) {
        User containedUser = userRepository.findByEmail(user.getEmail());
        if (containedUser != null) {
            logger.log(Level.ERROR, "User [" + user + "] already exists. Throwing new AlreadyAUserException...");
            throw new AlreadyAUserException("User with that email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            logger.log(Level.ERROR, "No user with id [" + id + "] Throwing UserNotFoundException...");
            throw new UserNotFoundException("No user with that id exists...");
        }

        return userOptional.get();
    }
}
