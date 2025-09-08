package org.ofss.alm.crm.engines.loan.decision;

import org.ofss.alm.enums.Tier;

public class LoanDecisionEngine {
    public boolean approve(Tier tier) {
        return tier != Tier.HIGH;
    }
}
