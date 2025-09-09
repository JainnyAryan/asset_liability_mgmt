package org.ofss.alm.crm.providers;

import org.ofss.alm.models.Asset;

import java.util.ArrayList;

public class AssetsProvider {
    private static ArrayList<Asset>  assets = new ArrayList<Asset>();

    public static void addAsset(Asset asset){
        assets.add(asset);
    }

    public static ArrayList<Asset> getAssets(){
        return assets;
    }
}
