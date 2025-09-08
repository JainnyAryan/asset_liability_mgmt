package org.ofss.alm.crm;

import java.util.ArrayList;
import java.util.List;

public class RiskManagementSystem {
    private final double exposureLimit;
    private final double minAvgRiskScore;
    private final List<Double> approvedRiskScores = new ArrayList<>();
    private double totalExposure = 0;

    public RiskManagementSystem(double exposureLimit, double minAvgRiskScore) {
        this.exposureLimit = exposureLimit;
        this.minAvgRiskScore = minAvgRiskScore;
    }

    public boolean canApproveLoan(double requestedAmount) {
        return (totalExposure + requestedAmount <= exposureLimit) &&
                (getAverageRiskScore() >= minAvgRiskScore);
    }

    public void recordApprovedLoan(double amount, double riskScore) {
        totalExposure += amount;
        approvedRiskScores.add(riskScore);
    }

    public double getTotalExposure() { return totalExposure; }

    public double getAverageRiskScore() {
        if (approvedRiskScores.isEmpty()) return 100;
        return approvedRiskScores.stream().mapToDouble(Double::doubleValue).average().orElse(100);
    }

    public String getStatus() {
        if (totalExposure >= exposureLimit)
            return "Exposure limit reached";
        if (getAverageRiskScore() < minAvgRiskScore)
            return "Average risk score too low";
        return "Safe to lend";
    }
}
