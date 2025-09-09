package org.ofss.alm.crm.utils;

import org.ofss.alm.models.LoanActivityRecord;
import org.ofss.alm.models.LoanApplication;
import org.ofss.alm.crm.services.RiskManagementService;
import org.ofss.alm.models.RiskActivityRecord;

import java.util.List;

public class ReportPrinter {

    public static void printLoanReport(RiskManagementService bankRisk, List<LoanActivityRecord> loanActivityRecords) {
        // Print Bank Overview Header
        System.out.println("\n--- Bank Overview ---");
        System.out.println("----------------------------------------------");
        System.out.printf("| %-15s | %-12s | %-10s | %-15s | %-20s |\n",
                "Loan Type", "Customer", "Status", "Requested Amount", "Loan Risk Score");
        System.out.println("----------------------------------------------");

        // Iterate through the list of loan applications and print their details
        for (LoanActivityRecord loanActivityRecord : loanActivityRecords) {
            System.out.printf("| %-15s | %-12s | %-10s | $%-14.2f | %-20s |\n",
                    loanActivityRecord.getLoan().getLoanApplication().getLoanType(), loanActivityRecord.getLoan().getLoanApplication().getCustomer().getName(),
                    (loanActivityRecord.isApproved() ? "Approved" : "Rejected"), loanActivityRecord.getLoan().getLoanApplication().getRequestedAmount(),
                    loanActivityRecord.getRiskScore());
        }

        System.out.println("----------------------------------------------");
    }

    public static void printRiskActivityReport(List<RiskActivityRecord> riskRecords) {
        System.out.println("\n--- Risk Monitoring Report ---");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-25s | %-15s | %-15s | %-10s | %-20s |\n",
                "Asset", "Total Exposure", "Avg Risk Score", "Status", "Recorded At");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (RiskActivityRecord record : riskRecords) {
            System.out.printf("| %-25s | $%-14.2f | %-15.2f | %-10s | %-20s |\n",
                    record.getAsset().getType(),
                    record.getTotalExposure(),
                    record.getAverageRiskScore(),
                    record.getRiskStatus(),
                    record.getCreatedAt().toString());
        }

        System.out.println("-----------------------------------------------------------------------------------------------------");
    }
}
