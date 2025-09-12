package org.ofss.alm.crm.DAO;
import org.ofss.alm.models.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerDAO {
    boolean createCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(UUID customerId);
    Customer getCustomerById(UUID customerId);
    List<Customer> getAllCustomers();
    boolean isCustomerExists(UUID customerId);
}
