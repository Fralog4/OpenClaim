package com.openClaim.OpenClaim.service;

import com.openClaim.OpenClaim.model.Claim;
import com.openClaim.OpenClaim.repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public List<Claim> getClaimsByPolicyId(Long policyId) {
        return claimRepository.findByPolicyId(policyId);
    }

    public Claim getClaimById(Long id) {
        return claimRepository.findById(id).orElse(null); //lancia ecce
    }

    public Claim saveClaim(Claim claim) {
       try{
           return claimRepository.save(claim);
       } catch (Exception e) {
           throw new RuntimeException("Error saving the claim "+e);
       }
    }

    public void deleteClaim(Long id){
        claimRepository.deleteById(id);
    }

}
