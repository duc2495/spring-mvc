package hrs.training.springmvcex1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hrs.training.springmvcex1.model.Customer;

@Service("customerService")
public interface CustomerService {

	void insert(Customer customer);

	Customer findByCustomerId(int custId);

	List<Customer> list();
}
