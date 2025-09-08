package org.ofss.alm.crm.services;

import org.ofss.alm.models.LoanApplication;

public class DisbursementService {
    public void disburse(LoanApplication app) {
        System.out.println("Loan of $" + app.getRequestedAmount() + " disbursed to customer.");
    }
}
