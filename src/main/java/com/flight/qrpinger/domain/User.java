package com.flight.qrpinger.domain;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "Users")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, length = 12)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return phoneNumber.equals(user.phoneNumber) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, email);
    }

    public boolean validate() {
        log.warning("validate");
        return true;
    }
}
