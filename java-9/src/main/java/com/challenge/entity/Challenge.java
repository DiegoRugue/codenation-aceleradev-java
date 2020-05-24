package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String slug;

    @OneToMany(mappedBy = "challenge")
    private List<Acceleration> accelerations = new ArrayList<>();

    @OneToMany(mappedBy = "id.challenge")
    private List<Submission> submissions = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
}
