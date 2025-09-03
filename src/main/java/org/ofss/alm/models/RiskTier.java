package org.ofss.alm.models;

import java.time.LocalDate;

public class RiskTier {
    private int id;
    private Customer customer;
    private String tierLevel; // Tier1, Tier2, etc.
    private String methodUsed; // e.g., "ConfigBased", "MLModel"
    private LocalDate assignedOn;
}
