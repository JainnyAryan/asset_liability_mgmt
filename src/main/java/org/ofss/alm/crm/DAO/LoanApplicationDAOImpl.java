package org.ofss.alm.crm.DAO;

package org.ofss.alm.crm.DAO
import org.ofss.alm.enums.LoanType;
import org.ofss.alm.models.Customer;
import org.ofss.alm.models.LoanApplication;
import org.ofss.alm.crm.utils.OracleDBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class LoanApplicationDAOImpl implements LoanApplicationDAO {
    private final Connection con = OracleDBConnection.getConnection();
    //add logger
    private static final String INSERT_QUERY = "INSERT INTO
    loan_applications VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM
    loan_applications WHERE id=?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM
    loan_applications WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM
    loan_applications";
    @Override
    public boolean createLoanApplication(LoanApplication loanApp) {
        try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
            ps.setObject(1, loanApp.getId());
            ps.setObject(2, loanApp.getCustomer().getId());
            ps.setInt(3, loanApp.getCreditScore());
            ps.setDouble(4, loanApp.getTotalDebt());
            ps.setInt(5, loanApp.getEmploymentYears());
            ps.setBoolean(6, loanApp.isGoodRepaymentHistory());
            ps.setDouble(7, loanApp.getIncome());
            ps.setString(8, loanApp.getEducationLevel());
            ps.setString(9, loanApp.getLoanType().name());
            ps.setDouble(10, loanApp.getRequestedAmount());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in createLoanApplication: " +
                    e.getMessage());
            return false;
        }
    }
    @Override
    public boolean updateLoanApplication(LoanApplication loanApp) {
        try (PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {
            ps.setObject(1, loanApp.getCustomer().getId());
            ps.setInt(2, loanApp.getCreditScore());
            ps.setDouble(3, loanApp.getTotalDebt());
            ps.setInt(4, loanApp.getEmploymentYears());
            ps.setBoolean(5, loanApp.isGoodRepaymentHistory());
            ps.setDouble(6, loanApp.getIncome());
            ps.setString(7, loanApp.getEducationLevel());
            ps.setString(8, loanApp.getLoanType().name());
            ps.setDouble(9, loanApp.getRequestedAmount());
            ps.setObject(10, loanApp.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in updateLoanApplication: " +
                    e.getMessage());
            return false;
        }
    }
    @Override
    public boolean deleteLoanApplication(UUID id) {
        try (PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in deleteLoanApplication: " +
                    e.getMessage());
            return false;
        }
    }
    @Override
    public LoanApplication getLoanApplication(UUID id) {
        try (PreparedStatement ps =
                     con.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToLoanApplication(rs);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getLoanApplication: " +
                    e.getMessage());
        }
        return null;
    }
    @Override
    public List<LoanApplication> getAllLoanApplications() {
        List<LoanApplication> list = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                list.add(mapResultSetToLoanApplication(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getAllLoanApplications: "
                    + e.getMessage());
        }
        return list;
    }
    @Override
    public boolean isLoanApplicationExists(UUID id) {
        try (PreparedStatement ps =
                     con.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("SQL Exception in isLoanApplicationExists: "
                    + e.getMessage());
            return false;
        }
    }
    private LoanApplication mapResultSetToLoanApplication(ResultSet rs)
            throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        UUID customerId = UUID.fromString(rs.getString("customer_id"));
        // You should fetch full Customer using CustomerDAO
        Customer customer = new Customer();
        customer.setId(customerId);
        return new LoanApplication(
                id,
                customer,
                rs.getInt("credit_score"),
                rs.getDouble("total_debt"),
                rs.getInt("employment_years"),
                rs.getBoolean("good_repayment_history"),
                rs.getDouble("income"),
                rs.getString("education_level"),
                LoanType.valueOf(rs.getString("loan_type")),
                rs.getDouble("requested_amount")
        );
    }
}