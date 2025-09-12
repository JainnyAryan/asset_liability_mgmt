package org.ofss.alm.crm.DAO;
import org.ofss.alm.enums.AssetType;
import org.ofss.alm.enums.CurrencyCode;
import org.ofss.alm.models.Asset;
import org.ofss.alm.models.Customer;
import org.ofss.alm.models.Loan;
import org.ofss.alm.crm.utils.OracleDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssetDAOImpl implements AssetDAO {

    private final Connection con = OracleDBConnection.getConnection();

    private static final String INSERT_QUERY = "INSERT INTO assets (id, customer_id, type, currency_code, amount, payout_date, interest_rate, loan_id, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM assets WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE assets SET customer_id=?, type=?, currency_code=?, amount=?, payout_date=?, interest_rate=?, loan_id=?, created_at=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM assets WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM assets";
    private static final String SELECT_BY_CUSTOMER_ID_QUERY = "SELECT * FROM assets WHERE customer_id = ?";

    @Override
    public boolean createAsset(Asset asset) {
        try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
            ps.setObject(1, asset.getId());
            ps.setObject(2, asset.getCustomer().getId());
            ps.setString(3, asset.getType().name());
            ps.setString(4, asset.getCurrencyCode().name());
            ps.setBigDecimal(5, asset.getAmount());
            ps.setDate(6, Date.valueOf(asset.getPayoutDate()));
            ps.setBigDecimal(7, asset.getInterestRate());
            ps.setObject(8, asset.getLoan() != null ? asset.getLoan().getId() : null);
            ps.setTimestamp(9, Timestamp.valueOf(asset.getCreatedAt()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in createAsset: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Asset getAsset(UUID id) {
        try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToAsset(rs);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getAsset: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateAsset(Asset asset) {
        try (PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {
            ps.setObject(1, asset.getCustomer().getId());
            ps.setString(2, asset.getType().name());
            ps.setString(3, asset.getCurrencyCode().name());
            ps.setBigDecimal(4, asset.getAmount());
            ps.setDate(5, Date.valueOf(asset.getPayoutDate()));
            ps.setBigDecimal(6, asset.getInterestRate());
            ps.setObject(7, asset.getLoan() != null ? asset.getLoan().getId() : null);
            ps.setTimestamp(8, Timestamp.valueOf(asset.getCreatedAt()));
            ps.setObject(9, asset.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in updateAsset: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAsset(UUID id) {
        try (PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in deleteAsset: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Asset> getAllAssets() {
        List<Asset> list = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                list.add(mapResultSetToAsset(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getAllAssets: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Asset> getAssetsByCustomerId(UUID customerId) {
        List<Asset> list = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(SELECT_BY_CUSTOMER_ID_QUERY)) {
            ps.setObject(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToAsset(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getAssetsByCustomerId: " + e.getMessage());
        }
        return list;
    }

    private Asset mapResultSetToAsset(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        UUID customerId = UUID.fromString(rs.getString("customer_id"));

        Customer customer = new Customer();
        customer.setId(customerId); // Optionally fetch full customer using CustomerDAO

        UUID loanId = rs.getObject("loan_id") != null ? UUID.fromString(rs.getString("loan_id")) : null;
        Loan loan = null;
        if (loanId != null) {
            loan = new Loan(); // Optionally fetch full loan using LoanDAO
            loan.setId(loanId);
        }

        return new Asset(
                id,
                customer,
                AssetType.valueOf(rs.getString("type")),
                CurrencyCode.valueOf(rs.getString("currency_code")),
                rs.getBigDecimal("amount"),
                rs.getDate("payout_date").toLocalDate(),
                rs.getBigDecimal("interest_rate"),
                loan,
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}
