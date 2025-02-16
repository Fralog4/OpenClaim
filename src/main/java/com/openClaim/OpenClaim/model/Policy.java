package com.openClaim.OpenClaim.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "policy")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Claim> claims;

    @OneToOne
    @JoinColumn(name = "policy_holder_id", nullable = false)
    private PolicyHolder policyHolder;
    private String policyNumber;

}
