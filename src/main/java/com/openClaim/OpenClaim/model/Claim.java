package com.openClaim.OpenClaim.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name= "claim")
public class Claim {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id",nullable = false)
    private Policy policy;
    @OneToOne
    @JoinColumn(name = "claimant_id")
    private Claimant claimant;
    @OneToOne
    @JoinColumn(name = "defendant_id")
    private Defendant defendant;
    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;
    private LocalDate claimDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;






}
