package hrs.training.springmvcex1.dao;

import java.util.List;

import hrs.training.springmvcex1.model.Customer;

public interface CustomerDAO {

	void insert(Customer customer);

	Customer findByCustomerId(int custId);

	List<Customer> listAll();
	List<Customer> findAll();

	List<Customer> getCustomersByPage(Integer offset, Integer maxResult);

	Long count();

	Integer getNextId();
}
