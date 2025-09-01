package org.ofss.alm.models.IRRM;

public class Bucket {
    public final String name;
    public final double minMonthsInclusive; // >=
    public final double maxMonthsExclusive; // < (except last bucket with Double.POSITIVE_INFINITY)

    public Bucket(String name, double minMonthsInclusive, double maxMonthsExclusive) {
        this.name = name;
        this.minMonthsInclusive = minMonthsInclusive;
        this.maxMonthsExclusive = maxMonthsExclusive;
    }

    public boolean contains(double months) {
        return months >= minMonthsInclusive && months < maxMonthsExclusive;
    }
}
