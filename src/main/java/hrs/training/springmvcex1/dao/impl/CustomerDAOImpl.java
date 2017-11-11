package hrs.training.springmvcex1.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import hrs.training.springmvcex1.dao.CustomerDAO;
import hrs.training.springmvcex1.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {
	private JdbcTemplate jdbcTemplate;

	public CustomerDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(Customer customer) {

		String sql = "INSERT INTO surveydb.customer (name, address, sex, school, year)" + " VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, customer.getName(), customer.getAddress(), customer.getSex(), customer.getSchool(),
				customer.getYear());
	}

	@Override
	public Customer findByCustomerId(int custId) {
	    String sql = "SELECT * FROM surveydb.customer WHERE customer_id=" + custId;
	    return jdbcTemplate.query(sql, new ResultSetExtractor<Customer>() {
	 
	        @Override
	        public Customer extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
		        	Customer aCustomer = new Customer();
		       	 
		        	aCustomer.setCustId(rs.getInt("customer_id"));
		        	aCustomer.setName(rs.getString("name"));
		        	aCustomer.setAddress(rs.getString("address"));
		        	aCustomer.setSex(rs.getString("sex"));
		        	aCustomer.setSchool(rs.getString("school"));
		        	aCustomer.setYear(rs.getInt("year"));
		        	
		            return aCustomer;
	            }
	 
	            return null;
	        }
	 
	    });
	}

	@Override
	public List<Customer> list() {
	    String sql = "SELECT * FROM surveydb.customer";
	    List<Customer> listCustomer = jdbcTemplate.query(sql, new RowMapper<Customer>() {
	 
	        @Override
	        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Customer aCustomer = new Customer();
	 
	        	aCustomer.setCustId(rs.getInt("customer_id"));
	        	aCustomer.setName(rs.getString("name"));
	        	aCustomer.setAddress(rs.getString("address"));
	        	aCustomer.setSex(rs.getString("sex"));
	        	aCustomer.setSchool(rs.getString("school"));
	        	aCustomer.setYear(rs.getInt("year"));
	        	
	            return aCustomer;
	        }
	 
	    });
	 
	    return listCustomer;
	}
}