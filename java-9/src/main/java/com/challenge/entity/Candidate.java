package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Candidate {
    @EmbeddedId
    private CandidateIdentity id;

    @NotNull
    private int status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
