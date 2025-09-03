package org.ofss.alm.crm.engines.loans.riskEvaluation;

import org.ofss.alm.crm.engines.LoanApplication;
import org.ofss.alm.models.Customer;

public class PersonalLoanRiskEvaluation {
    public double calculateRiskScore(LoanApplication app) {
        Customer c = app.getCustomer();
        double loanAmount = app.getRequestedAmount();

        double totalScore = 0;

        // Income-to-loan ratio (25 points)
        totalScore += Math.min((c.getIncome() / loanAmount) * 25, 25);

        // Credit score (25 points)
        totalScore += (c.getCreditScore() / 850.0) * 25;

        // DTI (15 points)
        double dti =
                app.getTotalDebt() / c.getIncome();
        totalScore += (dti < 0.3) ? 15 : (dti < 0.5 ? 10 : 5);

        // Repayment history (20 points)
        totalScore += app.isGoodRepaymentHistory() ? 20 : 10;

        // Age & Education (15 points)
        double ageScore = (app.getAge() >= 25 && app.getAge() <= 60) ? 7 : 3;
        double eduScore = switch (app.getEducationLevel().toLowerCase()) {
            case "phd", "master" -> 8;
            case "bachelor" -> 5;
            default -> 2;
        };
        totalScore += ageScore + eduScore;

        return Math.round(totalScore * 100.0) / 100.0;
    }
}
