package org.ofss.alm;

import org.ofss.alm.crm.processors.LoanProcessor;
import org.ofss.alm.crm.services.RiskManagementService;
import org.ofss.alm.enums.LoanType;
import org.ofss.alm.models.Customer;
import org.ofss.alm.models.LoanApplication;

import java.util.UUID;

public class App {
    public static void main(String[] args) {

        // Risk Management System
        RiskManagementService bankRisk = new RiskManagementService(500_000, 65); // limit, min avg score
        LoanProcessor processor = new LoanProcessor(bankRisk);

        // Create Customers
        Customer alice = new Customer("Alice Johnson", "Individual", "alice.johnson@example.com", 750000);
        Customer xyzCorp = new Customer( "XYZ Corp", "Corporate", "contact@xyzcorp.com", 5000000);

        // Create Loan Applications
        LoanApplication loanApp1 = new LoanApplication(
                alice, 720, 15000.0, 5, true, 30, "Master", LoanType.HOME, 250000.0
        );
        LoanApplication loanApp2 = new LoanApplication(
                xyzCorp, 680, 120000.0, 10, true, 45, "Bachelor", LoanType.PERSONAL, 1000000.0
        );

        // Process Loans
        processor.processLoan(loanApp1);
        processor.processLoan(loanApp2);

        // Output Bank Overview after Loan Processing
        System.out.println("\n--- Bank Overview ---");
        System.out.println("Total Exposure: $" + bankRisk.getTotalExposure());
        System.out.println("Avg Risk Score: " + bankRisk.getAverageRiskScore());
        System.out.println("Status: " + bankRisk.getStatus());
    }
}
