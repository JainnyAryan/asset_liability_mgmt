package org.ofss.alm.crm.utils;

import org.ofss.alm.models.LoanActivityRecord;
import org.ofss.alm.models.LoanApplication;
import org.ofss.alm.crm.services.RiskManagementService;

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
                    loanActivityRecord.getLoanApplication().getLoanType(), loanActivityRecord.getCustomer().getName(),
                    (loanActivityRecord.isApproved() ? "Approved" : "Rejected"), loanActivityRecord.getLoanApplication().getRequestedAmount(),
                    loanActivityRecord.getRiskScore());
        }

        System.out.println("----------------------------------------------");

        // Print Bank Risk Analysis Summary
        System.out.println("\n--- Bank Risk Analysis ---");
        System.out.printf("Total Exposure: $%.2f\n", bankRisk.getTotalExposure());
        System.out.printf("Avg Risk Score: %.2f\n", bankRisk.getAverageRiskScore());
        System.out.println("Status: " + bankRisk.getStatus());
    }
}
