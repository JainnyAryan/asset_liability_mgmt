package org.ofss.alm.crm.providers;

import org.ofss.alm.models.Loan;
import org.ofss.alm.models.RiskActivityRecord;

import java.util.ArrayList;

public class LoansProvider {
    private static ArrayList<Loan> loans =  new ArrayList<Loan>();

    public static void addLoan(Loan loan) {
        loans.add(loan);
    }

    public static ArrayList<Loan> getLoans() {
        return loans;
    }
}
