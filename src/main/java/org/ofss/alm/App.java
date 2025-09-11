package org.ofss.alm;

import org.ofss.alm.crm.processors.LoanProcessor;
import org.ofss.alm.crm.providers.LoanActivityRecordsProvider;
import org.ofss.alm.crm.providers.RiskActivityRecordsProvider;
import org.ofss.alm.crm.services.RiskManagementService;
import org.ofss.alm.crm.utils.ReportPrinter;
import org.ofss.alm.enums.LoanType;
import org.ofss.alm.models.Customer;
import org.ofss.alm.models.LoanApplication;

import java.util.List;
import java.util.UUID;

public class App {
    public static void main(String[] args) {

        // Risk Management System
        RiskManagementService bankRisk = new RiskManagementService(100_00_00_000, 40); // limit, min avg score
        LoanProcessor processor = new LoanProcessor(bankRisk);

        // ✅ Customers (ages in years)
        Customer c1 = new Customer("Alice Morgan", "Individual", "alice.morgan@example.com", 32);
        Customer c2 = new Customer("Bob Turner", "Individual", "bob.turner@example.com", 45);
        Customer c3 = new Customer("Carol White", "Individual", "carol.white@example.com", 38);
        Customer c4 = new Customer("David Brown", "Individual", "david.brown@example.com", 29);
        Customer c5 = new Customer("Eve Black", "Individual", "eve.black@example.com", 41);
        Customer c6 = new Customer("NeoTech Ltd", "Corporate", "contact@neotech.com", 50);
        Customer c7 = new Customer("Alpha Group", "Corporate", "info@alphagroup.com", 48);
        Customer c8 = new Customer("Delta Systems", "Corporate", "support@deltasystems.com", 52);
        Customer c9 = new Customer("Beta Holdings", "Corporate", "beta@holdings.com", 43);
        Customer c10 = new Customer("Gamma Enterprises", "Corporate", "gamma@enterprises.com", 55);



        // ✅ Loan Applications (income in ₹)
        List<LoanApplication> loans = List.of(
                // Alice Morgan
                new LoanApplication(c1, 740, 900000, 5, true, 10_00_000, "Master", LoanType.HOME, 250000),
                new LoanApplication(c1, 730, 850000, 6, true, 12_00_000, "Bachelor", LoanType.PERSONAL, 120000),

                // Bob Turner
                new LoanApplication(c2, 760, 950000, 10, true, 4_00_000, "PhD", LoanType.HOME, 220000),

                // Carol White
                new LoanApplication(c3, 720, 880000, 7, true, 8_00_000, "Master", LoanType.HOME, 180000),
                new LoanApplication(c3, 750, 930000, 8, true, 2_00_000, "Bachelor", LoanType.PERSONAL, 150000),
                new LoanApplication(c3, 770, 990000, 10, true, 20_00_000, "PhD", LoanType.PERSONAL, 1000000),

                // David Brown
                new LoanApplication(c4, 730, 870000, 6, true, 5_00_000, "Bachelor", LoanType.PERSONAL, 110000),

                // Eve Black
                new LoanApplication(c5, 740, 910000, 9, true, 11_00_000, "Master", LoanType.HOME, 200000),
                new LoanApplication(c5, 720, 890000, 8, true, 9_00_000, "Bachelor", LoanType.PERSONAL, 90000),

                // NeoTech Ltd
                new LoanApplication(c6, 780, 1500000, 12, true, 40_00_000, "PhD", LoanType.HOME, 300000),
                new LoanApplication(c6, 70, 140000000, 15, true, 38_00_000, "MBA", LoanType.PERSONAL, 200000000),

                // Alpha Group
                new LoanApplication(c7, 740, 1300000, 10, true, 35_00_000, "Master", LoanType.HOME, 250000),
                new LoanApplication(c7, 720, 1250000, 8, true, 30_00_000, "Bachelor", LoanType.PERSONAL, 100000),

                // Delta Systems
                new LoanApplication(c8, 730, 1100000, 11, true, 28_00_000, "MBA", LoanType.HOME, 180000),

                // Beta Holdings
                new LoanApplication(c9, 750, 1450000, 13, true, 36_00_000, "PhD", LoanType.HOME, 280000),
                new LoanApplication(c9, 770, 1600000, 14, true, 40_00_000, "MBA", LoanType.PERSONAL, 190000),

                // Gamma Enterprises
                new LoanApplication(c10, 760, 1350000, 12, true, 32_00_000, "PhD", LoanType.HOME, 22_60_00_000),
                new LoanApplication(c10, 740, 1250000, 10, true, 30_00_000, "Master", LoanType.PERSONAL, 150000)
        );



        for(LoanApplication loanApplication : loans){
            processor.processLoan(loanApplication);
        }

        ReportPrinter.printLoanReport(LoanActivityRecordsProvider.getLoanActivityRecords());
        ReportPrinter.printRiskActivityReport(RiskActivityRecordsProvider.getRiskActivityRecords());
    }
}
