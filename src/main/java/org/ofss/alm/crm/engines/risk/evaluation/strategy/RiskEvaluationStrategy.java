package org.ofss.alm.crm.engines.risk.evaluation.strategy;

import org.ofss.alm.models.LoanApplication;

public interface RiskEvaluationStrategy {
    double calculateRiskScore(LoanApplication app);
}