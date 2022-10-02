package com.flight.qrpinger.service.user;

import com.flight.qrpinger.domain.User;

public interface UserService {

    User saveUser(User user);

    User getUser(Long id);

}
