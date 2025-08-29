package org.ofss.alm.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long transactionId;
    private Long assetId;     // One of assetId or liabilityId must be set
    private Long liabilityId;
    private String type; // Disbursement, Repayment, InterestPayment, Deposit, Withdrawal
    private BigDecimal amount;
    private LocalDateTime transactionDate;

    // Getters & Setters
    // ...
}