package com.openClaim.OpenClaim.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum ClaimStatus {
    @Enumerated(EnumType.STRING)
    OPEN,
    @Enumerated(EnumType.STRING)
    CLOSED,
    @Enumerated(EnumType.STRING)
    DRAFT

}
