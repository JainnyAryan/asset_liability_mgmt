package org.ofss.alm.models;

import java.math.BigDecimal;

public class ScenarioResult {
    private Long scenarioResultId;
    private Long scenarioId;  // FK -> Scenario
    private Long assetId;     // One of assetId or liabilityId must be set
    private Long liabilityId;
    private BigDecimal projectedDuration;
    private BigDecimal projectedConvexity;
    private BigDecimal projectedNii;
    private BigDecimal projectedLiquidityImpact;

    // Getters & Setters
    // ...
}