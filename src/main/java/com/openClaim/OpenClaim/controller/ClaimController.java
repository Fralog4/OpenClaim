package com.openClaim.OpenClaim.controller;

import com.openClaim.OpenClaim.model.Claim;
import com.openClaim.OpenClaim.service.ClaimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openClaim")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/claims/{id}")
    public Claim getClaim(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }

    @GetMapping("/claims/{policyId}")
    public List<Claim> getClaimsByPolicyId(@PathVariable Long policyId) {
        return claimService.getClaimsByPolicyId(policyId);
    }

    @PostMapping("/claims")
    public Claim saveClaim(@RequestBody Claim claim) {
        return claimService.saveClaim(claim);
    }

    @DeleteMapping("/claims/{id}")
    public void deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
    }
}
