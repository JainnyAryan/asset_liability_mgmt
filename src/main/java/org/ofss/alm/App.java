package org.ofss.alm;

import org.ofss.alm.models.Asset;
import org.ofss.alm.models.Customer;
import org.ofss.alm.models.Liability;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.ofss.alm.models.lrm.LiquidityRiskChecker.LiquidityCoverageCalculator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // The Below Customer Represents the Bank Itself
        Customer customer = new Customer();
        customer.setId(1); // CUSTOMERID of the Bank must bot be assigned to anything else
        customer.setName("LOCAL_BANK");
        customer.setType("LOCAL_BANK_VAULT");
        customer.setBirthDate(LocalDate.now());

        // Asset Creation for the Bank Vault (Does not mean bank assets , this represents Liquidity Reserves , Not an Asset, does not earn Interest)
        ArrayList<Asset> AssetList =  new ArrayList<>();
        AssetList.add(new Asset(1,1,"cash",200,LocalDate.now(),4.5,"rupee"));
        AssetList.add(new Asset(2,1,"bond",50,LocalDate.now().minusDays(20),4.5,"rupee"));
        AssetList.add(new Asset(3,1,"bond",50,LocalDate.now().plusDays(20),4.5,"rupee"));


        // Bank LiablityList
        ArrayList<Liability> LiabilityList =   new ArrayList<>();
        LiabilityList.add(new Liability(1,2,"deposit",30,LocalDate.of(2025, 9, 1),4.5,"rupee"));
        LiabilityList.add(new Liability(2,3,"deposit",30,LocalDate.of(2025, 8, 10),4.5,"rupee"));
        LiabilityList.add(new Liability(3,4,"deposit",30,LocalDate.of(2025, 10, 16),4.5,"rupee"));
        LiabilityList.add(new Liability(4,5,"deposit",30,LocalDate.of(2025, 4, 3),4.5,"rupee"));
        LiabilityList.add(new Liability(5,6,"deposit",30,LocalDate.of(2025, 7, 1),4.5,"rupee"));
        LiabilityList.add(new Liability(6,7,"deposit",30,LocalDate.of(2025, 9, 23),4.5,"rupee"));
        LiabilityList.add(new Liability(7,8,"deposit",30,LocalDate.of(2025, 11, 2),4.5,"rupee"));
        LiabilityList.add(new Liability(8,9,"deposit",30,LocalDate.of(2025, 6, 4),4.5,"rupee"));


        // Calculation of LCR :
        System.out.println("LCR Percentage is : " + LiquidityCoverageCalculator(customer,LiabilityList,AssetList,LocalDate.now()));
    }
}
