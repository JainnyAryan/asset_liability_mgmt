package org.ofss.alm.crm.utils;

import org.ofss.alm.models.LoanActivityRecord;
import org.ofss.alm.models.LoanApplication;
import org.ofss.alm.crm.services.RiskManagementService;
import org.ofss.alm.models.RiskActivityRecord;

import java.util.List;

public class ReportPrinter {

    public static void printLoanReport(List<LoanActivityRecord> loanActivityRecords) {
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
        System.out.printf("|  | %-15s | %-15s | %-10s | %-20s |\n",
               "Total Exposure", "Avg Risk Score", "Status", "Recorded At");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (RiskActivityRecord record : riskRecords) {
//            String assetType = (record.getAsset() != null) ? record.getAsset().getType().toString() : "ASSET_NOT_DISBURSED";

            System.out.printf("|| $%-14.2f | %-15.2f | %-10s | %-20s |\n",
//                    assetType,
                    record.getTotalExposure(),
                    record.getAverageRiskScore(),
                    record.getRiskStatus(),
                    record.getCreatedAt().toString());
        }

        System.out.println("-----------------------------------------------------------------------------------------------------");
    }
}
