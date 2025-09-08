package org.ofss.alm.crm.providers;

import org.ofss.alm.models.LoanActivityRecord;

import java.util.ArrayList;

public class LoanActivityRecordsProvider {
    private static ArrayList<LoanActivityRecord> loanActivityRecords;
    public LoanActivityRecordsProvider() {
        loanActivityRecords = new ArrayList<LoanActivityRecord>();
    }

    public static ArrayList<LoanActivityRecord> getLoanActivityRecords() {
        return loanActivityRecords;
    }

    public static void addLoanActivityRecord(LoanActivityRecord loanActivityRecord) {
        loanActivityRecords.add(loanActivityRecord);
    }
}
