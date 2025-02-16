package com.openClaim.OpenClaim.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "defendant")
public class Defendant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;
    private String defendantName;
    private String defendantLastName;
    private String defendantEmail;
    private String defendantPhoneNumber;
    private String defendantAddress;
    private String plateNumber;


}
