package org.ofss.alm.crm.DAO;
import org.ofss.alm.models.Asset;
import java.util.List;
import java.util.UUID;

public interface AssetDAO {
    boolean createAsset(Asset asset);
    Asset getAsset(UUID id);
    boolean updateAsset(Asset asset);
    boolean deleteAsset(UUID id);
    List<Asset> getAllAssets();
    List<Asset> getAssetsByCustomerId(UUID customerId);
}
