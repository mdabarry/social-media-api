package com.example.webservices.restfulwebservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
@Entity(name = "person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private LocalDate birthDate;

}
