package com.openClaim.OpenClaim.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "claimant")
public class Claimant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String claimantName;

    @OneToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;
    private String claimantLastName;
    private String claimantEmail;
    private String claimantPhoneNumber;
    private String claimantAddress;
    private String plateNumber;
    private Date birthDate;
    private String gender;

}
