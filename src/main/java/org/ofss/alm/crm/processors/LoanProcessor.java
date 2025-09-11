package org.ofss.alm.crm.processors;

import org.ofss.alm.crm.providers.AssetsProvider;
import org.ofss.alm.crm.providers.LoanActivityRecordsProvider;
import org.ofss.alm.crm.providers.LoansProvider;
import org.ofss.alm.crm.providers.RiskActivityRecordsProvider;
import org.ofss.alm.crm.services.RiskManagementService;
import org.ofss.alm.crm.engines.RiskScoreEngine;
import org.ofss.alm.crm.engines.decision.LoanDecisionEngine;
import org.ofss.alm.crm.services.DisbursementService;
import org.ofss.alm.crm.services.MonitoringService;
import org.ofss.alm.crm.engines.risk.tier.classifier.RiskTierClassifier;
import org.ofss.alm.enums.AssetType;
import org.ofss.alm.enums.CurrencyCode;
import org.ofss.alm.enums.RiskTier;
import org.ofss.alm.models.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        // Step 1: Calculate risk score and classify tier
        double score = riskEngine.calculateTotalScore(app);
        RiskTier riskTier = classifier.classify(score);
        boolean isLoanRisky = decisionEngine.isLoanRisky(riskTier);

        // Print evaluation
        printLoanEvaluation(app, score, riskTier, isLoanRisky);

        // Create the Loan object upfront and link to application
        Loan loan = new Loan(app);

        // Create LoanActivityRecord and set the Loan
        LoanActivityRecord loanActivityRecord = new LoanActivityRecord();
        loanActivityRecord.setLoan(loan);
        loanActivityRecord.setRiskScore(score);
        loanActivityRecord.setRiskTier(riskTier);
        loanActivityRecord.setLoanRisky(isLoanRisky);

        // Always add LoanActivityRecord
        LoanActivityRecordsProvider.addLoanActivityRecord(loanActivityRecord);

        // Rejected due to high risk
        if (isLoanRisky) {
            System.out.println("Loan rejected: Applicant risk too high.");
            recordRiskActivity(null);  // No asset since loan not disbursed
            return;
        }

        // Rejected due to bank policy
        if (!riskManager.canApproveLoan(app.getRequestedAmount())) {
            System.out.println("Loan rejected: Bank risk policy does not allow more lending.");
            recordRiskActivity(null);  // No asset since loan not disbursed
            return;
        }

        // Approved
        loanActivityRecord.setApproved(true);
        LoansProvider.addLoan(loan);

        // Create asset
        Asset asset = new Asset();
        asset.setAmount(BigDecimal.valueOf(app.getRequestedAmount()));
        asset.setCurrencyCode(CurrencyCode.INR);
        asset.setCustomer(app.getCustomer());
        asset.setType(AssetType.LOAN);
        asset.setInterestRate(BigDecimal.valueOf(10));
        asset.setPayoutDate(LocalDate.from(loan.getCreatedAt().plus(Asset.payoutDurationOffsetDays)));
        asset.setLoan(loan);
        AssetsProvider.addAsset(asset);

        // Record risk activity with asset
        recordRiskActivity(asset);

        disbursement.disburse(app);
        monitoring.monitor(app);

        // Update exposure and risk records only after disbursal
        riskManager.recordApprovedLoan(app.getRequestedAmount(), score);

        System.out.println("Loan approved and disbursed.");
    }

    private void recordRiskActivity(Asset asset) {
        RiskActivityRecord record = new RiskActivityRecord();
        record.setAsset(asset); // may be null
        record.setAverageRiskScore(riskManager.getAverageRiskScore());
        record.setTotalExposure(riskManager.getTotalExposure());
        record.setRiskStatus(riskManager.getStatus());
        RiskActivityRecordsProvider.addRiskActivityRecord(record);
    }




    private void printLoanEvaluation(LoanApplication app, double score, RiskTier tier, boolean isRisky) {
        System.out.println("\n--- Loan Evaluation ---");
        System.out.println("Applicant: " + app.getCustomer().getName());
        System.out.println("Score: " + score + ", Tier: " + tier + ", isLoanRisky: " + isRisky);
        System.out.println("Requested Amount: ₹" + app.getRequestedAmount());
        System.out.println("Current Bank Status: " + riskManager.getStatus());
    }

}
