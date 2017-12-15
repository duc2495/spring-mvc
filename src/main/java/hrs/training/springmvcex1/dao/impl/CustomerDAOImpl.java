package hrs.training.springmvcex1.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import hrs.training.springmvcex1.dao.CustomerDAO;
import hrs.training.springmvcex1.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public CustomerDAOImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(Customer customer) {
		String sql1 = "INSERT INTO Customer (name, birthday, address, gender, school, school_year)"
				+ "VALUES ( :name, :birthday, :address, :gender, :school, :schoolYear)";
		namedParameterJdbcTemplate.update(sql1, getCustomerParameter(customer));
		if (!customer.getLanguages().isEmpty()) {
			String sql2 = "INSERT INTO Customer_Language (customer_id, language_id)"
					+ "VALUES ( :customer_id, :language_id)";
			namedParameterJdbcTemplate.update(sql2, getCustomerLanguageParameter(customer));
		}
	}

	@Override
	public Customer findByCustomerId(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		String sql = "SELECT * FROM Customer WHERE customer_id=:id";

		Customer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new CustomerMapper());
		} catch (EmptyResultDataAccessException e) {
			//
		}
		return result;
	}

	@Override
	public List<Customer> listAll() {
		String sql = "SELECT * FROM Customer";
		List<Customer> listCustomer = namedParameterJdbcTemplate.query(sql, new CustomerMapper());
		return listCustomer;
	}

	private SqlParameterSource getCustomerParameter(Customer customer) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", this.getNextId());
		paramSource.addValue("name", customer.getName());
		paramSource.addValue("birthday", customer.getBirthday());
		paramSource.addValue("address", customer.getAddress());
		paramSource.addValue("gender", customer.getGender());
		paramSource.addValue("school", customer.getSchool());
		paramSource.addValue("schoolYear", customer.getSchoolYear());

		return paramSource;
	}

	private SqlParameterSource getCustomerLanguageParameter(Customer customer) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("languages", customer.getLanguages());
		for (int i = 0; i < customer.getLanguages().size(); i++) {
			paramSource.addValue("customer_id", customer.getId());
			paramSource.addValue("language_id", customer.getLanguages().get(i).getId());
		}

		return paramSource;
	}

	@Override
	public List<Customer> getCustomersByPage(Integer offset, Integer maxResult) {
		if (offset == null) {
			offset = 0;
		}
		String sql = "SELECT * FROM Customer LIMIT " + maxResult + " OFFSET " + offset;
		List<Customer> listCustomer = namedParameterJdbcTemplate.query(sql, new CustomerMapper());
		return listCustomer;
	}

	@Override
	public Long count() {
		String sql = "SELECT count(*) FROM Customer";
		return jdbcTemplate.queryForObject(sql, new Object[] {}, Long.class);
	}

	private static final class CustomerMapper implements RowMapper<Customer> {

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer aCustomer = new Customer();
			aCustomer.setId(rs.getInt("customer_id"));
			aCustomer.setName(rs.getString("name"));
			aCustomer.setBirthday(rs.getDate("birthday"));
			aCustomer.setAddress(rs.getString("address"));
			aCustomer.setGender(rs.getString("gender"));
			aCustomer.setSchool(rs.getString("school"));
			aCustomer.setSchoolYear(rs.getInt("school_year"));
			return aCustomer;
		}
	}

	@Override
	public Integer getNextId() {
		String sql = "Select nextval(pg_get_serial_sequence('customer', 'customer_id'))";
		return jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
	}

}