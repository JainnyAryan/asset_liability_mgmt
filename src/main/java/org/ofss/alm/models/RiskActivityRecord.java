package org.ofss.alm.models;

import org.ofss.alm.enums.RiskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class RiskActivityRecord {
    private UUID id;
    private Asset asset;
    private double totalExposure;
    private double averageRiskScore;
    private RiskStatus riskStatus;
    private LocalDateTime createdAt;

    public RiskActivityRecord() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public RiskActivityRecord(Asset asset, double totalExposure, double averageRiskScore, RiskStatus riskStatus) {
        this.asset = asset;
        this.totalExposure = totalExposure;
        this.averageRiskScore = averageRiskScore;
        this.riskStatus = riskStatus;
    }

    public RiskActivityRecord(UUID id, Asset asset, double totalExposure, double averageRiskScore, RiskStatus riskStatus,  LocalDateTime createdAt) {
        this.id = id;
        this.asset = asset;
        this.totalExposure = totalExposure;
        this.averageRiskScore = averageRiskScore;
        this.riskStatus = riskStatus;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public double getTotalExposure() {
        return totalExposure;
    }

    public void setTotalExposure(double totalExposure) {
        this.totalExposure = totalExposure;
    }

    public double getAverageRiskScore() {
        return averageRiskScore;
    }

    public void setAverageRiskScore(double averageRiskScore) {
        this.averageRiskScore = averageRiskScore;
    }

    public RiskStatus getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(RiskStatus riskStatus) {
        this.riskStatus = riskStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
