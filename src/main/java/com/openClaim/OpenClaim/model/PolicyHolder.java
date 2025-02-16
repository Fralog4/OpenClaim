package com.openClaim.OpenClaim.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "policy_holder")
public class PolicyHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "policyHolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Policy policy;
    private String policyHolderName;
    private String policyHolderLastName;
    private String policyHolderEmail;
    private String policyHolderPhoneNumber;
    private String policyHolderAddress;
    private String plateNumber;

}
