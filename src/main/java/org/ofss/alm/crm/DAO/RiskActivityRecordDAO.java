package org.ofss.alm.crm.DAO;
import org.ofss.alm.models.RiskActivityRecord;

import java.util.List;
import java.util.UUID;

public interface RiskActivityRecordDAO {
    boolean createRecord(RiskActivityRecord record);
    RiskActivityRecord getRecordById(UUID id);
    List<RiskActivityRecord> getAllRecords();
    List<RiskActivityRecord> getRecordsByAssetId(UUID assetId);
    boolean deleteRecord(UUID id);
}
