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
    public Long getScenarioResultId() {
        return scenarioResultId;
    }

    public void setScenarioResultId(Long scenarioResultId) {
        this.scenarioResultId = scenarioResultId;
    }

    public Long getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Long scenarioId) {
        this.scenarioId = scenarioId;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Long getLiabilityId() {
        return liabilityId;
    }

    public void setLiabilityId(Long liabilityId) {
        this.liabilityId = liabilityId;
    }

    public BigDecimal getProjectedDuration() {
        return projectedDuration;
    }

    public void setProjectedDuration(BigDecimal projectedDuration) {
        this.projectedDuration = projectedDuration;
    }

    public BigDecimal getProjectedConvexity() {
        return projectedConvexity;
    }

    public void setProjectedConvexity(BigDecimal projectedConvexity) {
        this.projectedConvexity = projectedConvexity;
    }

    public BigDecimal getProjectedNii() {
        return projectedNii;
    }

    public void setProjectedNii(BigDecimal projectedNii) {
        this.projectedNii = projectedNii;
    }

    public BigDecimal getProjectedLiquidityImpact() {
        return projectedLiquidityImpact;
    }

    public void setProjectedLiquidityImpact(BigDecimal projectedLiquidityImpact) {
        this.projectedLiquidityImpact = projectedLiquidityImpact;
    }
}