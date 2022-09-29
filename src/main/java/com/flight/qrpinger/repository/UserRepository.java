package com.flight.qrpinger.repository;

import com.flight.qrpinger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
