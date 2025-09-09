package org.ofss.alm.crm.providers;

import org.ofss.alm.models.LoanActivityRecord;
import org.ofss.alm.models.RiskActivityRecord;

import java.util.ArrayList;

public class RiskActivityRecordsProvider {
    private static ArrayList<RiskActivityRecord> riskActivityRecords =  new ArrayList<RiskActivityRecord>();

    public static void addRiskActivityRecord(RiskActivityRecord riskActivityRecord) {
        riskActivityRecords.add(riskActivityRecord);
    }

    public static ArrayList<RiskActivityRecord> getRiskActivityRecords() {
        return riskActivityRecords;
    }
}
