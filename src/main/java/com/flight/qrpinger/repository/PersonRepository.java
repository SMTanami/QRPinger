package com.flight.qrpinger.repository;

import com.flight.qrpinger.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
