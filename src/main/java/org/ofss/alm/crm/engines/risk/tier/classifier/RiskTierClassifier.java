package org.ofss.alm.crm.engines.risk.tier.classifier;

import org.ofss.alm.enums.RiskTier;

public class RiskTierClassifier {
    public RiskTier classify(double score) {
        if (score >= 80) return RiskTier.VERY_LOW;
        if(score >= 65) return RiskTier.LOW;
        if (score >= 50) return RiskTier.MEDIUM;
        return RiskTier.HIGH;
    }
}
