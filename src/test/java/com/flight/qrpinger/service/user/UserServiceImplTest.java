package com.flight.qrpinger.service.user;

import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.exceptions.UserNotFoundException;
import com.flight.qrpinger.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    public static final User USER = User.builder()
            .id(1L)
            .firstName("John")
            .lastName("Doe")
            .phoneNumber("+1555247555")
            .email("qrpinger@gmail.com").build();
    @Mock
    private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserServiceImpl(userRepository);
    }

    @Test
    void saveUser() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(USER);
        User saved = userService.saveUser(USER);

        assertNotNull(saved);
        Mockito.verify(userRepository).save(Mockito.eq(USER));
    }

    @Test
    void getUser() {
        Mockito.when(userRepository.findById(USER.getId())).thenReturn(Optional.of(USER));
        User receivedUser = userService.getUser(USER.getId());

        assertEquals(receivedUser, USER);
        Mockito.verify(userRepository).findById(Mockito.any());
    }

    @Test
    void getUserNotFoundExceptionTest() {
        Exception e = assertThrows(UserNotFoundException.class, () -> userService.getUser(-1L));
        assertEquals("No user with that id exists", e.getMessage());
        Mockito.verify(userRepository).findById(Mockito.any());
    }
}