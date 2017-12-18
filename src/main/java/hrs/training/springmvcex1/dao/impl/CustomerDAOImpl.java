package hrs.training.springmvcex1.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import hrs.training.springmvcex1.dao.CustomerDAO;
import hrs.training.springmvcex1.model.Customer;
import hrs.training.springmvcex1.model.Language;

public class CustomerDAOImpl implements CustomerDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public CustomerDAOImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(Customer customer) {
		customer.setId(this.getNextId());
		String sql1 = "INSERT INTO Customer (customer_id, name, birthday, address, gender, school, school_year)"
				+ "VALUES (:id, :name, :birthday, :address, :gender, :school, :schoolYear)";
		namedParameterJdbcTemplate.update(sql1, getCustomerParameter(customer));
		if (!customer.getLanguages().isEmpty()) {
			insertBatch(customer);
		}
	}

	// insert batch
	public void insertBatch(Customer customer) {

		String sql = "INSERT INTO Customer_Language (customer_id, language_id)" + "VALUES (?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Language language = customer.getLanguages().get(i);
				ps.setInt(1, customer.getId());
				ps.setInt(2, language.getId());
			}

			@Override
			public int getBatchSize() {
				return customer.getLanguages().size();
			}
		});
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
		paramSource.addValue("id", customer.getId());
		paramSource.addValue("name", customer.getName());
		paramSource.addValue("birthday", customer.getBirthday());
		paramSource.addValue("address", customer.getAddress());
		paramSource.addValue("gender", customer.getGender());
		paramSource.addValue("school", customer.getSchool());
		paramSource.addValue("schoolYear", customer.getSchoolYear());

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
	public List<Customer> findAll() {
		String sql = "SELECT * FROM CUSTOMER C " + " INNER JOIN CUSTOMER_LANGUAGE CL ON C.customer_id = CL.customer_id"
				+ " INNER JOIN LANGUAGE L ON CL.language_id = L.language_id";
		List<Customer> customers = new ArrayList<Customer>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			int currentId = 0;
			List<Language> languages = new ArrayList<Language>();
			if (currentId == (Integer) row.get("customer_id")) {
				languages.add(new Language((Integer) (row.get("customer_id")), (String) row.get("name")));
			} else {
				currentId = (Integer) row.get("customer_id");
				Customer customer = new Customer();
				customer.setId((Integer) (row.get("customer_id")));
				customer.setName((String) row.get("name"));
				customer.setBirthday((Date) row.get("birthday"));
				customer.setAddress((String) row.get("address"));
				customer.setGender((String) row.get("gender"));
				customer.setSchool((String) row.get("school"));
				customer.setSchoolYear((Integer) row.get("school_year"));
				customer.setLanguages(languages);
				customers.add(customer);
				languages = new ArrayList<Language>();
			}
		}
		return customers;
	}

	@Override
	public Integer getNextId() {
		String sql = "Select nextval(pg_get_serial_sequence('customer', 'customer_id'))";
		return jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
	}

}