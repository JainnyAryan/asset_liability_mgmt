package org.ofss.alm.crm.engines;

import org.ofss.alm.models.LoanApplication;

public interface RiskEvaluationStrategy {
    double calculateRiskScore(LoanApplication app);
}