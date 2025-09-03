import org.ofss.alm.models.LoanApplication;

public class RiskEvaluationEngine {
    public double calculateTotalScore(LoanApplication app) {
        RiskEvaluationStrategy strategy;

        switch (app.getLoanType()) {
            case PERSONAL -> strategy = new PersonalLoanRiskEvaluation();
            case HOME -> strategy = new HomeLoanRiskEvaluation();
            // case BUSINESS -> strategy = new BusinessLoanRiskEvaluation();
            default -> throw new IllegalArgumentException("Unsupported loan type");
        }

        return strategy.calculateRiskScore(app);
    }
}
