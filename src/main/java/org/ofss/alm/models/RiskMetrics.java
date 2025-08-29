package org.ofss.alm.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RiskMetrics {
    private Long riskMetricsId;
    private Long assetId;     // One of assetId or liabilityId must be set
    private Long liabilityId;
    private BigDecimal duration;
    private BigDecimal convexity;
    private BigDecimal niiContribution;
    private BigDecimal liquidityImpact;
    private LocalDateTime lastCalculatedOn;

    // Getters & Setters
    public Long getRiskMetricsId() {
        return riskMetricsId;
    }

    public void setRiskMetricsId(Long riskMetricsId) {
        this.riskMetricsId = riskMetricsId;
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

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public BigDecimal getConvexity() {
        return convexity;
    }

    public void setConvexity(BigDecimal convexity) {
        this.convexity = convexity;
    }

    public BigDecimal getNiiContribution() {
        return niiContribution;
    }

    public void setNiiContribution(BigDecimal niiContribution) {
        this.niiContribution = niiContribution;
    }

    public BigDecimal getLiquidityImpact() {
        return liquidityImpact;
    }

    public void setLiquidityImpact(BigDecimal liquidityImpact) {
        this.liquidityImpact = liquidityImpact;
    }

    public LocalDateTime getLastCalculatedOn() {
        return lastCalculatedOn;
    }

    public void setLastCalculatedOn(LocalDateTime lastCalculatedOn) {
        this.lastCalculatedOn = lastCalculatedOn;
    }
}
