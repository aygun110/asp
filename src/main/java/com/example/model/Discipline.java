package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}