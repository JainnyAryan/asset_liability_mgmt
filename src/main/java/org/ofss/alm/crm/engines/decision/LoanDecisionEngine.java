package org.ofss.alm.crm.engines.decision;

import org.ofss.alm.enums.RiskTier;

public class LoanDecisionEngine {
    public boolean isLoanRisky(RiskTier tier) {
        return tier == RiskTier.HIGH;
    }
}
