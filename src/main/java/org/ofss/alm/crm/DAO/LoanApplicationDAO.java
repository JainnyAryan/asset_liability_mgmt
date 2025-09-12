package org.ofss.alm.crm.DAO;
import org.ofss.alm.models.LoanApplication;
import java.util.List;
import java.util.UUID;
public interface LoanApplicationDAO {
    boolean createLoanApplication(LoanApplication loanApplication);

    boolean updateLoanApplication(LoanApplication loanApplication);

    boolean deleteLoanApplication(UUID id);

    LoanApplication getLoanApplication(UUID id);

    List<LoanApplication> getAllLoanApplications();

    boolean isLoanApplicationExists(UUID id);
}