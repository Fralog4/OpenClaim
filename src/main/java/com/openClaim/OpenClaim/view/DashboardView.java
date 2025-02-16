package com.openClaim.OpenClaim.view;

import com.openClaim.OpenClaim.model.Claim;
import com.openClaim.OpenClaim.model.Policy;
import com.openClaim.OpenClaim.service.ClaimService;
import com.openClaim.OpenClaim.service.PolicyService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@PermitAll
@Route("/dashboard")
public class DashboardView extends VerticalLayout {

    private final PolicyService policyService;
    private final ClaimService claimService;
    private final Grid<Claim> claimGrid;
    private final ComboBox<Policy> policyComboBox;

    public DashboardView(PolicyService policyService, ClaimService claimService) {
        this.policyService = policyService;
        this.claimService = claimService;

        policyComboBox = new ComboBox<>("Seleziona una polizza");
        policyComboBox.setItemLabelGenerator(Policy::getPolicyNumber);
        policyComboBox.setItems(policyService.getAllPolicies());
        policyComboBox.addValueChangeListener(event -> loadClaims(event.getValue()));
        policyComboBox.addClassName("policy-combo-box");

        claimGrid = new Grid<>(Claim.class);
        claimGrid.setColumns("id", "claimDate", "claimStatus", "claimant", "defendant", "policy");
        claimGrid.addClassName("claim-grid");

        Button refreshButton = new Button("Aggiorna", e -> refreshPolicies());
        refreshButton.addClassName("refresh-button");

        Button goBackToHome = new Button("Torna alla Home");
        goBackToHome.addClassName("back-to-home-button");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/openclaim/home");
        });

        add(policyComboBox, refreshButton, claimGrid, goBackToHome);
    }

    private void loadClaims(Policy policy) {
        if (policy != null) {
            List<Claim> claims = claimService.getClaimsByPolicyId(policy.getId());
            claimGrid.setItems(claims);
        } else {
            claimGrid.setItems(List.of());
        }
    }

    private void refreshPolicies() {
        policyComboBox.setItems(policyService.getAllPolicies());
    }
}
