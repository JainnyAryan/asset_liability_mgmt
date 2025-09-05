package org.ofss.alm.models.lrm;



import org.ofss.alm.models.Asset;
import org.ofss.alm.models.Customer;
import org.ofss.alm.models.Liability;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class LiquidityRiskChecker {

    private static final double WITHDRAW_RATE = 7;

    public static boolean isWithin30Days(LocalDate baseDate, LocalDate checkDate) {
        // Strip years → work with MonthDay
        MonthDay mdA = MonthDay.from(baseDate);
        MonthDay mdB = MonthDay.from(checkDate);

        // Normalize both into the same year (say 2000)
        LocalDate normalizedA = mdA.atYear(2000);
        LocalDate normalizedB = mdB.atYear(2000);

        // Handle year wrapping (e.g. Dec → Jan)
        if (normalizedB.isBefore(normalizedA)) {
            normalizedB = normalizedB.plusYears(1);
        }

        long daysBetween = ChronoUnit.DAYS.between(normalizedA, normalizedB);
        return daysBetween >= 0 && daysBetween <= 30;
    }

    public static double LiquidityCoverageCalculator(Customer localBank, ArrayList<Liability> LiabilityList , ArrayList<Asset> AssetList, LocalDate currentDate)
    {
        double highLiquidityAssetValuation = 0;
        double expectedCashOutFlow = 0;
        ArrayList<Asset> bankAssetList = new ArrayList<>();

        for(Asset asset : AssetList)
        {
            if(asset.getCustomerId() == localBank.getCustomerId())
            {
                highLiquidityAssetValuation = highLiquidityAssetValuation + asset.getLiquidationRate(currentDate);
            }
        }

        double totalLiabilityValue = 0;
        double totalLiabilityInterestValue = 0;

        for(Liability liability : LiabilityList)
        {
            totalLiabilityValue = totalLiabilityValue + liability.getPrincipleAmount();
            if(isWithin30Days(currentDate,liability.getAnnualInterestPayBackDate()))
            {
                totalLiabilityInterestValue = totalLiabilityInterestValue + (liability.getInterestRate() * liability.getPrincipleAmount()/100.0);
            }
        }

        expectedCashOutFlow = (totalLiabilityValue*WITHDRAW_RATE/100.0) + totalLiabilityInterestValue;

        return highLiquidityAssetValuation/expectedCashOutFlow * 100;
    }
}
