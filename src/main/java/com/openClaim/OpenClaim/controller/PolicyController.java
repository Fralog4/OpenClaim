package com.openClaim.OpenClaim.controller;

import com.openClaim.OpenClaim.model.Policy;
import com.openClaim.OpenClaim.service.PolicyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/{id}")
    public Policy getPolicy(@PathVariable Long id) {
        return policyService.retrievePolicyFromId(id).orElse(null);
    }

    @PostMapping
    public void savePolicy(@RequestBody Policy policy) {
        policyService.savePolicy(policy);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
    }
}
