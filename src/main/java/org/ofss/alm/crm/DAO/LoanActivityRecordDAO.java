package org.ofss.alm.crm.DAO;
import org.ofss.alm.models.LoanActivityRecord;

import java.util.List;
import java.util.UUID;

public interface LoanActivityRecordDAO {
    boolean createRecord(LoanActivityRecord record);
    LoanActivityRecord getRecordById(UUID id);
    List<LoanActivityRecord> getAllRecords();
    List<LoanActivityRecord> getRecordsByLoanId(UUID loanId);
    boolean deleteRecord(UUID id);
}
