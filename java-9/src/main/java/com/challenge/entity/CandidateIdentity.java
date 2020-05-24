package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class CandidateIdentity implements Serializable {
    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Acceleration acceleration;

    @NotNull
    @ManyToOne
    private Company company;
}