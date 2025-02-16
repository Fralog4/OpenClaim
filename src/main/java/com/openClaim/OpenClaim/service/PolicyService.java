package com.openClaim.OpenClaim.service;

import com.openClaim.OpenClaim.model.Policy;
import com.openClaim.OpenClaim.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }


    public Optional<Policy> retrievePolicyFromId(Long policyId) {
        return policyRepository.findById(policyId);
    }

    public void deletePolicy(Long policyId) {
        policyRepository.deleteById(policyId);
    }


    public void savePolicy(Policy policy) {
        policyRepository.save(policy);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
}
