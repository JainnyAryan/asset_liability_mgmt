package org.ofss.alm.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RiskMetrics {
    private Long riskMetricsId;
    private Long assetId;     // One of assetId or liabilityId must be set
    private Long liabilityId;
    private BigDecimal duration;
    private BigDecimal convexity;
    private BigDecimal niiContribution;
    private BigDecimal liquidityImpact;
    private LocalDateTime lastCalculatedOn;

    // Getters & Setters
    // ...
}
