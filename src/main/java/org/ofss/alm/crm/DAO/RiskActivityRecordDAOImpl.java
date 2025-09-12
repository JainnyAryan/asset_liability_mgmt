package org.ofss.alm.dao;

import org.ofss.alm.crm.DAO.RiskActivityRecordDAO;
import org.ofss.alm.enums.RiskStatus;
import org.ofss.alm.models.Asset;
import org.ofss.alm.models.RiskActivityRecord;
import org.ofss.alm.crm.utils.OracleDBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RiskActivityRecordDAOImpl implements RiskActivityRecordDAO {

    private final Connection con = OracleDBConnection.getConnection();

    private static final String INSERT_QUERY = "INSERT INTO risk_activity_records (id, asset_id, total_exposure, average_risk_score, risk_status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM risk_activity_records WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM risk_activity_records";
    private static final String SELECT_BY_ASSET_ID_QUERY = "SELECT * FROM risk_activity_records WHERE asset_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM risk_activity_records WHERE id = ?";

    @Override
    public boolean createRecord(RiskActivityRecord record) {
        try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
            ps.setObject(1, record.getId());
            ps.setObject(2, record.getAsset().getId());
            ps.setDouble(3, record.getTotalExposure());
            ps.setDouble(4, record.getAverageRiskScore());
            ps.setString(5, record.getRiskStatus().name());
            ps.setTimestamp(6, Timestamp.valueOf(record.getCreatedAt()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in createRecord: " + e.getMessage());
            return false;
        }
    }

    @Override
    public RiskActivityRecord getRecordById(UUID id) {
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
    public List<RiskActivityRecord> getAllRecords() {
        List<RiskActivityRecord> list = new ArrayList<>();
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
    public List<RiskActivityRecord> getRecordsByAssetId(UUID assetId) {
        List<RiskActivityRecord> list = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ASSET_ID_QUERY)) {
            ps.setObject(1, assetId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToRecord(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getRecordsByAssetId: " + e.getMessage());
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

    private RiskActivityRecord mapResultSetToRecord(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        UUID assetId = UUID.fromString(rs.getString("asset_id"));

        Asset asset = new Asset();
        asset.setId(assetId); // Populate full asset via AssetDAO if needed

        double totalExposure = rs.getDouble("total_exposure");
        double avgRiskScore = rs.getDouble("average_risk_score");
        RiskStatus status = RiskStatus.valueOf(rs.getString("risk_status"));
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

        return new RiskActivityRecord(id, asset, totalExposure, avgRiskScore, status, createdAt);
    }
}
