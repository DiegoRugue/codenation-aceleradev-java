package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Submission {
    @EmbeddedId
    private SubmissionIdentity id;

    @NotNull
    private float score;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
