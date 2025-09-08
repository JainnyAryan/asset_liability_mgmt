package org.ofss.alm;

import org.ofss.alm.crm.RiskManagementSystem;
import org.ofss.alm.crm.engines.RiskEvaluationEngine;
import org.ofss.alm.crm.engines.loan.decision.LoanDecisionEngine;
import org.ofss.alm.crm.services.DisbursementService;
import org.ofss.alm.crm.services.MonitoringService;
import org.ofss.alm.crm.tier.classifier.TierClassifier;
import org.ofss.alm.enums.Tier;
import org.ofss.alm.models.LoanApplication;

public class LoanProcessor {
    private final RiskEvaluationEngine riskEngine = new RiskEvaluationEngine();
    private final TierClassifier classifier = new TierClassifier();
    private final LoanDecisionEngine decisionEngine = new LoanDecisionEngine();
    private final DisbursementService disbursement = new DisbursementService();
    private final MonitoringService monitoring = new MonitoringService();
    private final RiskManagementSystem riskManager;

    public LoanProcessor(RiskManagementSystem riskManager) {
        this.riskManager = riskManager;
    }

    public void processLoan(LoanApplication app) {
        double score = riskEngine.calculateTotalScore(app);
        Tier tier = classifier.classify(score);
        boolean approved = decisionEngine.approve(tier);

        System.out.println("\n--- Loan Evaluation ---");
        System.out.println("Score: " + score + ", Tier: " + tier + ", Approved: " + approved);
        System.out.println("Bank Status: " + riskManager.getStatus());

        if (!approved) {
            System.out.println("Loan rejected: Applicant risk too high.");
            return;
        }

        if (!riskManager.canApproveLoan(app.getRequestedAmount())) {
            System.out.println("Loan rejected: Bank risk policy does not allow more lending.");
            return;
        }

        disbursement.disburse(app);
        monitoring.monitor(app);
        riskManager.recordApprovedLoan(app.getRequestedAmount(), score);
        System.out.println("Loan approved and disbursed.");
    }
}
