package org.ofss.alm.crm.DAO;

import org.ofss.alm.enums.RiskTier;
import org.ofss.alm.models.Loan;
import org.ofss.alm.models.LoanActivityRecord;
import org.ofss.alm.crm.utils.OracleDBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoanActivityRecordDAOImpl implements LoanActivityRecordDAO {

    private final Connection con = OracleDBConnection.getConnection();

    private static final String INSERT_QUERY = "INSERT INTO loan_activity_records (id, loan_id, risk_score, risk_tier, is_loan_risky, is_approved, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM loan_activity_records WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM loan_activity_records";
    private static final String SELECT_BY_LOAN_ID_QUERY = "SELECT * FROM loan_activity_records WHERE loan_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM loan_activity_records WHERE id = ?";

    @Override
    public boolean createRecord(LoanActivityRecord record) {
        try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
            ps.setObject(1, record.getId());
            ps.setObject(2, record.getLoan().getId());
            ps.setDouble(3, record.getRiskScore());
            ps.setString(4, record.getRiskTier().name());
            ps.setBoolean(5, record.isLoanRisky());
            ps.setBoolean(6, record.isApproved());
            ps.setTimestamp(7, Timestamp.valueOf(record.getCreatedAt()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in createRecord: " + e.getMessage());
            return false;
        }
    }

    @Override
    public LoanActivityRecord getRecordById(UUID id) {
        try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToRecord(rs);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getRecordById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<LoanActivityRecord> getAllRecords() {
        List<LoanActivityRecord> list = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                list.add(mapResultSetToRecord(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getAllRecords: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<LoanActivityRecord> getRecordsByLoanId(UUID loanId) {
        List<LoanActivityRecord> list = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(SELECT_BY_LOAN_ID_QUERY)) {
            ps.setObject(1, loanId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToRecord(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getRecordsByLoanId: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean deleteRecord(UUID id) {
        try (PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in deleteRecord: " + e.getMessage());
            return false;
        }
    }

    private LoanActivityRecord mapResultSetToRecord(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        UUID loanId = UUID.fromString(rs.getString("loan_id"));

        Loan loan = new Loan(); // Fetch full Loan object via LoanDAO if needed
        loan.setId(loanId);

        double riskScore = rs.getDouble("risk_score");
        RiskTier riskTier = RiskTier.valueOf(rs.getString("risk_tier"));
        boolean isLoanRisky = rs.getBoolean("is_loan_risky");
        boolean isApproved = rs.getBoolean("is_approved");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

        return new LoanActivityRecord(id, loan, riskScore, riskTier, isLoanRisky, isApproved, createdAt);
    }
}
