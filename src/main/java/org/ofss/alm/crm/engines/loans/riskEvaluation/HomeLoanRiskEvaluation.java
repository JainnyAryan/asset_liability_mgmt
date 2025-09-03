package org.ofss.alm.crm.engines.loans.riskEvaluation;

import org.ofss.alm.crm.engines.RiskEvaluationStrategy;
import org.ofss.alm.models.LoanApplication;
import org.ofss.alm.models.Customer;

public class HomeLoanRiskEvaluation implements RiskEvaluationStrategy {


    public double calculateRiskScore(LoanApplication app) {
        Customer c = app.getCustomer();
        double loanAmount = app.getRequestedAmount();

        double totalScore = 0;

        // Home loans weigh employment stability and repayment history more
        totalScore += Math.min((c.getIncome() / loanAmount) * 20, 20);
        totalScore += (c.getCreditScore() / 850.0) * 15;
        totalScore += Math.min(app.getEmploymentYears(), 10) * 2; // Max 20
        totalScore += app.isGoodRepaymentHistory() ? 25 : 10;
        totalScore += (app.getTotalDebt() / c.getIncome() < 0.3) ? 10 : 5;

        // Age & Education (10 points)
        double ageScore = (app.getAge() >= 30 && app.getAge() <= 65) ? 5 : 2;
        double eduScore = switch (app.getEducationLevel().toLowerCase()) {
            case "phd", "master" -> 5;
            case "bachelor" -> 3;
            default -> 1;
        };
        totalScore += ageScore + eduScore;

        return Math.round(totalScore * 100.0) / 100.0;
    }
}
