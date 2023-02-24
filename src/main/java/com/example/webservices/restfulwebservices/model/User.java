package com.example.webservices.restfulwebservices.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
@Entity
@Table(name = "people")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private LocalDate birthDate;

}
