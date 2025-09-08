package org.ofss.alm.models.IRRM;

import org.ofss.alm.models.Asset;
import org.ofss.alm.models.Customer;
import org.ofss.alm.models.Liability;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GapAnalysis {
    public static final List<Bucket> BUCKETS = Arrays.asList(
            new Bucket("0-1M", 0, 1),
            new Bucket("1-3M", 1, 3),
            new Bucket("3-6M", 3, 6),
            new Bucket("6-12M", 6, 12),
            new Bucket("1-2Y (12-24M)", 12, 24),
            new Bucket("2-3Y (24-36M)", 24, 36),
            new Bucket("3-5Y (36-60M)", 36, 60),
            new Bucket("5Y+ (>=60M)", 60, Double.POSITIVE_INFINITY)
    );

    private static BigDecimal calculateAccruedInterest(BigDecimal principal, BigDecimal rate, LocalDate startDate, LocalDate today) {
        long daysElapsed = ChronoUnit.DAYS.between(startDate, today);
        if (daysElapsed <= 0) return BigDecimal.ZERO;
        BigDecimal timeFraction = BigDecimal.valueOf(daysElapsed).divide(BigDecimal.valueOf(365), 10, RoundingMode.HALF_UP);
        return principal.multiply(rate).multiply(timeFraction).setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        // SAMPLE CUSTOMERS (not used heavily in this demo but shown for completeness)
        Customer c1 = new Customer(1L, "Mr. Sharma", "Individual", "sharma@example.com");
        Customer c2 = new Customer(2L, "Acme Corp", "Corporate", "acme@example.com");

        // SAMPLE ASSETS (typical market-like values)
        List<Asset> assets = Arrays.asList(
                // large 5-year loan
                new Asset(1L, 1L, "Loan", "INR",
                        new BigDecimal("1000000.00"), today.plusYears(5), new BigDecimal("0.05"),today.minusMonths(6)),
                // 6-month bond
                new Asset(2L, 2L, "Bond", "INR",
                        new BigDecimal("200000.00"), today.plusMonths(6), new BigDecimal("0.04"),today.minusMonths(4)),
                // 12-month loan
                new Asset(3L, 1L, "Loan", "INR",
                        new BigDecimal("500000.00"), today.plusMonths(12), new BigDecimal("0.06"),today.minusMonths(8))
        );

        // SAMPLE LIABILITIES (deposits, borrowings)
        List<Liability> liabilities = Arrays.asList(
                // 12-month deposit
                new Liability(1L, 2L, "Deposit", "INR",
                        new BigDecimal("800000.00"), today.plusMonths(12), new BigDecimal("0.03"),today.minusMonths(12)),
                // 1-month short-term deposit/savings
                new Liability(2L, 1L, "Savings", "INR",
                        new BigDecimal("200000.00"), today.plusMonths(1), new BigDecimal("0.01"),today.minusMonths(3)),
                // long-term borrowing 7 years
                new Liability(3L, 2L, "Borrowing", "INR",
                        new BigDecimal("300000.00"), today.plusYears(7), new BigDecimal("0.055"),today.minusMonths(18))
        );

        // run analysis
        GapAnalysis.GapResult gap = analyzeGap(assets, liabilities, today);

        // print nicely
        printGapResult(gap, assets, liabilities, today);
    }

    static class GapResult {
        public final Map<String, BigDecimal> assetsByBucket;
        public final Map<String, BigDecimal> liabilitiesByBucket;
        public final Map<String, BigDecimal> gapByBucket;
        public final Map<String, BigDecimal> cumulativeGapByBucket;

        public GapResult(Map<String, BigDecimal> a, Map<String, BigDecimal> l,
                         Map<String, BigDecimal> g, Map<String, BigDecimal> cg) {
            this.assetsByBucket = a;
            this.liabilitiesByBucket = l;
            this.gapByBucket = g;
            this.cumulativeGapByBucket = cg;
        }
    }

    // ----------------------------
    // Core analysis method
    // ----------------------------
    public static GapResult analyzeGap(List<Asset> assets, List<Liability> liabilities, LocalDate today) {
        Map<String, BigDecimal> assetsByBucket = new LinkedHashMap<>();
        Map<String, BigDecimal> liabilitiesByBucket = new LinkedHashMap<>();

        // Initialize all buckets to zero
        for (Bucket b : BUCKETS) {
            assetsByBucket.put(b.name, BigDecimal.ZERO);
            liabilitiesByBucket.put(b.name, BigDecimal.ZERO);
        }

        // Accumulate accrued interest for each asset into the correct bucket
        for (Asset a : assets) {
            LocalDate mat = a.getMaturityDate();
            if (mat == null || a.getCreatedDate() == null) continue;

            long daysToMaturity = ChronoUnit.DAYS.between(today, mat);
            if (daysToMaturity < 0) continue;

            double months = daysToMaturity / 30.0;
            String bucketName = bucketForMonths(months);

            BigDecimal accruedInterest = calculateAccruedInterest(
                    a.getAmount(), a.getInterestRate(), a.getCreatedDate(), today);

            assetsByBucket.put(bucketName,
                    assetsByBucket.get(bucketName).add((a.getAmount()).add(accruedInterest)));
        }

        // Accumulate accrued interest for each liability into the correct bucket
        for (Liability l : liabilities) {
            LocalDate mat = l.getMaturityDate();
            if (mat == null || l.getCreatedDate() == null) continue;

            long daysToMaturity = ChronoUnit.DAYS.between(today, mat);
            if (daysToMaturity < 0) continue;

            double months = daysToMaturity / 30.0;
            String bucketName = bucketForMonths(months);

            BigDecimal accruedInterest = calculateAccruedInterest(
                    l.getAmount(), l.getInterestRate(), l.getCreatedDate(), today);

            liabilitiesByBucket.put(bucketName,
                    liabilitiesByBucket.get(bucketName).add((l.getAmount()).add(accruedInterest)));
        }

        // Compute gap and cumulative gap
        Map<String, BigDecimal> gapByBucket = new LinkedHashMap<>();
        Map<String, BigDecimal> cumulativeGap = new LinkedHashMap<>();
        BigDecimal runningTotal = BigDecimal.ZERO;

        for (Bucket b : BUCKETS) {
            BigDecimal assetVal = assetsByBucket.get(b.name);
            BigDecimal liabilityVal = liabilitiesByBucket.get(b.name);
            BigDecimal gap = assetVal.subtract(liabilityVal);

            gapByBucket.put(b.name, gap);
            runningTotal = runningTotal.add(gap);
            cumulativeGap.put(b.name, runningTotal);
        }

        return new GapResult(assetsByBucket, liabilitiesByBucket, gapByBucket, cumulativeGap);
    }


    private static String bucketForMonths(double months) {
        for (Bucket b : BUCKETS) {
            if (b.contains(months)) return b.name;
        }
        // safe fallback
        return "Unknown";
    }

    // ----------------------------
    // Printing helpers (nice formatting)
    // ----------------------------
    private static final DecimalFormat DF = new DecimalFormat("#,##0.00");
    private static String fmt(BigDecimal v) {
        return (v == null) ? "0.00" : DF.format(v.setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
    private static void printGapResult(GapResult r, List<Asset> assets, List<Liability> liabilities, LocalDate today) {
        System.out.println("==== GAP ANALYSIS BASED ON ACCRUED INTEREST ====");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s%n", "BUCKET", "ASSETS (INT)", "LIABILITIES (INT)", "GAP(A-L)", "CUMULATIVE GAP");
        for (Bucket b : BUCKETS) {
            String name = b.name;
            BigDecimal a = r.assetsByBucket.get(name);
            BigDecimal l = r.liabilitiesByBucket.get(name);
            BigDecimal gap = r.gapByBucket.get(name);
            BigDecimal cum = r.cumulativeGapByBucket.get(name);
            System.out.printf("%-20s %-15s %-15s %-15s %-15s%n",
                    name,
                    fmt(a),
                    fmt(l),
                    fmt(gap),
                    fmt(cum));
        }

        // Interest report
        System.out.println("\n---- ACCRUED INTEREST REPORT ----");
        System.out.printf("%-10s %-10s %-15s %-15s %-15s%n", "TYPE", "ID", "AMOUNT", "RATE", "ACCRUED INT");
        for (Asset a : assets) {
            BigDecimal interest = calculateAccruedInterest(a.getAmount(), a.getInterestRate(), a.getCreatedDate(), today);
            System.out.printf("%-10s %-10d %-15s %-15s %-15s%n",
                    "Asset", a.getAssetId(), fmt(a.getAmount()), fmt(a.getInterestRate().multiply(BigDecimal.valueOf(100))) + "%", fmt(interest));
        }
        for (Liability l : liabilities) {
            BigDecimal interest = calculateAccruedInterest(l.getAmount(), l.getInterestRate(), l.getCreatedDate(), today);
            System.out.printf("%-10s %-10d %-15s %-15s %-15s%n",
                    "Liability", l.getLiabilityId(), fmt(l.getAmount()), fmt(l.getInterestRate().multiply(BigDecimal.valueOf(100))) + "%", fmt(interest));
        }

        System.out.println("\nNotes:");
        System.out.println("- Interest = Principal × Rate × (Days Elapsed / 365)");
        System.out.println("- Accrued Interest is calculated from createdDate to today.");
    }
}
