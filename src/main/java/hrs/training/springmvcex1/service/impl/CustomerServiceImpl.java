package hrs.training.springmvcex1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrs.training.springmvcex1.dao.CustomerDAO;
import hrs.training.springmvcex1.model.Customer;
import hrs.training.springmvcex1.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	CustomerDAO custDao;

	@Autowired
	public void setCustomerDao(CustomerDAO custDao) {
		this.custDao = custDao;
	}
	
	@Override
	public void insert(Customer customer) {
		custDao.insert(customer);
	}

	@Override
	public Customer findByCustomerId(int custId){
		return custDao.findByCustomerId(custId);
	}
	
	@Override
	public List<Customer> listAll(){
		return custDao.listAll();
	}
	
	@Override
	public List<Customer> findAll(){
		return custDao.findAll();
	}


	@Override
	public List<Customer> getCustomersByPage(Integer offset, Integer maxResult) {
		return custDao.getCustomersByPage(offset, maxResult);
	}

	@Override
	public Long count() {
		return custDao.count();
	}
}
