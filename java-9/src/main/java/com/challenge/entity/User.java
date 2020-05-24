package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String fullName;

    @NotNull
    @NotBlank
    @Email
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String nickname;

    @NotNull
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "id.user")
    private List<Submission> submissions = new ArrayList<>();

    @OneToMany(mappedBy = "id.user")
    private List<Candidate> candidates = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
}
