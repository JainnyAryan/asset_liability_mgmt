package org.ofss.alm.crm.tier.classifier;

import org.ofss.alm.enums.Tier;

public class TierClassifier {
    public Tier classify(double score) {
        if (score >= 80) return Tier.LOW;
        if (score >= 60) return Tier.MEDIUM;
        return Tier.HIGH;
    }
}
