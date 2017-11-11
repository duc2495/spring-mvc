package hrs.training.springmvcex1.dao;

import java.util.List;

import hrs.training.springmvcex1.model.Customer;

public interface CustomerDAO {

	public void insert(Customer customer);

	public Customer findByCustomerId(int custId);
	
	public List<Customer> list();
}
