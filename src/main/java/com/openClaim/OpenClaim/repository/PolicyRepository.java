package com.openClaim.OpenClaim.repository;

import com.openClaim.OpenClaim.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
