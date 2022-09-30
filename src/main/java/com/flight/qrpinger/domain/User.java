package com.flight.qrpinger.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "Users")
@ToString
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
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
}
