package org.ofss.alm.crm.engines.loan.risk.evaluation.strategy;

import org.ofss.alm.models.LoanApplication;

public interface RiskEvaluationStrategy {
    double calculateRiskScore(LoanApplication app);
}