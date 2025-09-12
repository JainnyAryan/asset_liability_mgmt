package org.ofss.alm.crm.DAO;
import org.ofss.alm.models.Customer;
import org.ofss.alm.crm.utils.OracleDBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerDAOImpl implements CustomerDAO {

    private final Connection con = OracleDBConnection.getConnection();

    private static final String INSERT_QUERY = "INSERT INTO customers (id, name, type, email, age, created_at) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE customers SET name=?, type=?, email=?, age=?, created_at=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM customers WHERE id=?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM customers WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM customers";

    @Override
    public boolean createCustomer(Customer customer) {
        try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
            ps.setObject(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getType());
            ps.setString(4, customer.getEmail());
            ps.setInt(5, customer.getAge());
            ps.setTimestamp(6, Timestamp.valueOf(customer.getCreatedAt()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in createCustomer: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try (PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getType());
            ps.setString(3, customer.getEmail());
            ps.setInt(4, customer.getAge());
            ps.setTimestamp(5, Timestamp.valueOf(customer.getCreatedAt()));
            ps.setObject(6, customer.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in updateCustomer: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(UUID customerId) {
        try (PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
            ps.setObject(1, customerId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception in deleteCustomer: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setObject(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCustomer(rs);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getCustomerById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                list.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in getAllCustomers: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean isCustomerExists(UUID customerId) {
        try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY)) {
            ps.setObject(1, customerId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("SQL Exception in isCustomerExists: " + e.getMessage());
            return false;
        }
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String name = rs.getString("name");
        String type = rs.getString("type");
        String email = rs.getString("email");
        int age = rs.getInt("age");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

        return new Customer(id, name, type, email, age, createdAt);
    }
}
