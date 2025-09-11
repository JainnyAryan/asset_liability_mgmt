package org.ofss.alm.crm.engines.risk.evaluation;

import org.ofss.alm.crm.engines.risk.evaluation.strategy.RiskEvaluationStrategy;
import org.ofss.alm.models.LoanApplication;
import org.ofss.alm.models.Customer;

public class HomeLoanRiskEvaluation implements RiskEvaluationStrategy {

    @Override
    public double calculateRiskScore(LoanApplication app) {
        Customer c = app.getCustomer();
        double income = app.getIncome();
        double loanAmount = app.getRequestedAmount();
        double totalDebt = app.getTotalDebt();
        double creditScore = c.getCreditScore();
        int employmentYears = app.getEmploymentYears();
        boolean goodRepaymentHistory = app.isGoodRepaymentHistory();
        int age = c.getAge();
        String education = app.getEducationLevel().toLowerCase();

        // 🔒 HARD RULES — immediate rejection
        double incomeToLoanRatio = income / loanAmount;
        if (incomeToLoanRatio < 0.05) {
            // Income is less than 5% of loan amount — auto-reject
            System.out.println("❌ Hard rejection: Income too low relative to loan amount.");
            return 0;
        }

        double debtToIncome = totalDebt / income;
        if (debtToIncome > 10) {
            // Debt-to-income ratio too high
            System.out.println("❌ Hard rejection: Debt-to-income ratio exceeds limit.");
            return 0;
        }

        // ✅ Scoring begins
        double totalScore = 0;

        // 💰 Income-to-loan ratio (Max 30 points)
        totalScore += Math.min(incomeToLoanRatio * 300, 30);

        // 💳 Credit score (Max 15 points)
        totalScore += (creditScore / 850.0) * 15;

        // 👔 Employment stability (Max 20)
        totalScore += Math.min(employmentYears, 10) * 2;

        // 📈 Repayment history
        totalScore += goodRepaymentHistory ? 25 : 10;

        // 📊 Debt-to-income ratio (Max 10)
        if (debtToIncome < 0.3) {
            totalScore += 10;
        } else if (debtToIncome < 0.5) {
            totalScore += 7;
        } else if (debtToIncome < 0.75) {
            totalScore += 5;
        } else {
            totalScore += 2;
        }

        // 👤 Age (Max 5)
        if (age >= 30 && age <= 65) {
            totalScore += 5;
        } else {
            totalScore += 2;
        }

        // 🎓 Education (Max 5)
        double eduScore = switch (education) {
            case "phd", "master" -> 5;
            case "bachelor" -> 3;
            default -> 1;
        };
        totalScore += eduScore;

        return Math.round(totalScore * 100.0) / 100.0;
    }
}
