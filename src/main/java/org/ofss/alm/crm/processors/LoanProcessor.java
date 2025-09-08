package org.ofss.alm.crm.processors;

import org.ofss.alm.crm.services.RiskManagementService;
import org.ofss.alm.crm.engines.RiskScoreEngine;
import org.ofss.alm.crm.engines.decision.LoanDecisionEngine;
import org.ofss.alm.crm.services.DisbursementService;
import org.ofss.alm.crm.services.MonitoringService;
import org.ofss.alm.crm.engines.risk.tier.classifier.RiskTierClassifier;
import org.ofss.alm.enums.RiskTier;
import org.ofss.alm.models.LoanActivityRecord;
import org.ofss.alm.models.LoanApplication;

public class LoanProcessor {
    private final RiskScoreEngine riskEngine = new RiskScoreEngine();
    private final RiskTierClassifier classifier = new RiskTierClassifier();
    private final LoanDecisionEngine decisionEngine = new LoanDecisionEngine();
    private final DisbursementService disbursement = new DisbursementService();
    private final MonitoringService monitoring = new MonitoringService();
    private final RiskManagementService riskManager;

    public LoanProcessor(RiskManagementService riskManager) {
        this.riskManager = riskManager;
    }

    public void processLoan(LoanApplication app) {
        LoanActivityRecord loanActivityRecord = new LoanActivityRecord();

        double score = riskEngine.calculateTotalScore(app);
        RiskTier riskTier = classifier.classify(score);
        boolean isLoanRisky = decisionEngine.isLoanRisky(riskTier);

        System.out.println("\n--- Loan Evaluation ---");
        System.out.println("Score: " + score + ", Tier: " + riskTier + ", isLoanRisky: " + isLoanRisky);
        System.out.println("Bank Status: " + riskManager.getStatus());



        if (isLoanRisky) {
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
