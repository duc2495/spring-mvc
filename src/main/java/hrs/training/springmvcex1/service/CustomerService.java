package hrs.training.springmvcex1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hrs.training.springmvcex1.model.Customer;

@Service("customerService")
public interface CustomerService {

	@Transactional
	void insert(Customer customer);

	Customer findByCustomerId(int custId);

	List<Customer> listAll();
	List<Customer> findAll();
	
	List<Customer> getCustomersByPage(Integer offset, Integer maxResult);
	
	Long count();
}
