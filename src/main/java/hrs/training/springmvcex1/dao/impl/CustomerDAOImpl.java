package hrs.training.springmvcex1.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
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
		String sql1 = "INSERT INTO CUSTOMER (customer_id, name, birthday, address, gender, school, school_year)"
				+ "VALUES (:id, :name, :birthday, :address, :gender, :school, :schoolYear)";
		namedParameterJdbcTemplate.update(sql1, getCustomerParameter(customer));
		if (!customer.getLanguages().isEmpty()) {
			insertBatch(customer);
		}
	}

	// insert language of customer
	public void insertBatch(Customer customer) {

		String sql = "INSERT INTO CUSTOMER_LANGUAGE (customer_id, language_id)" + "VALUES (?, ?)";

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
	public void delete(int id) {
		String sql1 = "DELETE FROM CUSTOMER_LANGUAGE WHERE customer_id = " + id + "";
		jdbcTemplate.update(sql1);
		String sql2 = "DELETE FROM CUSTOMER WHERE customer_id = " + id + "";
		jdbcTemplate.update(sql2);

	}

	@Override
	public Customer findByCustomerId(int id) {
		String sql = "SELECT  C.customer_id, C.name, C.birthday, C.address, C.gender, C.school, C.school_year, L.language_id, L.language "
				+ "FROM (SELECT * FROM CUSTOMER WHERE C.customer_id = " + id + ") C "
				+ "LEFT JOIN (SELECT CL.customer_id, CL.language_id, L.language FROM CUSTOMER_LANGUAGE CL "
				+ "INNER JOIN LANGUAGE L ON CL.language_id = L.language_id ) L ON C.customer_id = L.customer_id "
				+ "ORDER BY C.customer_id";
		List<Customer> customers = getCustomersBySql(sql);
		return customers.get(0);
	}

	@Override
	public List<Customer> getCustomersByPage(Integer offset, Integer maxResult) {
		if (offset == null) {
			offset = 0;
		}
		String sql = "SELECT  C.customer_id, C.name, C.birthday, C.address, C.gender, C.school, C.school_year, L.language_id, L.language "
				+ "FROM (SELECT * FROM CUSTOMER LIMIT " + maxResult + " OFFSET " + offset + ") C "
				+ "LEFT JOIN (SELECT CL.customer_id, CL.language_id, L.language FROM CUSTOMER_LANGUAGE CL "
				+ "INNER JOIN LANGUAGE L ON CL.language_id = L.language_id ) L ON C.customer_id = L.customer_id "
				+ "ORDER BY C.customer_id";
		return getCustomersBySql(sql);
	}

	@Override
	public Long count() {
		String sql = "SELECT count(*) FROM CUSTOMER";
		return jdbcTemplate.queryForObject(sql, new Object[] {}, Long.class);
	}

	@Override
	public List<Customer> findAll() {
		String sql = "SELECT  C.customer_id, C.name, C.birthday, C.address, C.gender, C.school, C.school_year, L.language_id, L.language "
				+ "FROM CUSTOMER C "
				+ "LEFT JOIN (SELECT CL.customer_id, CL.language_id, L.language FROM CUSTOMER_LANGUAGE CL "
				+ "INNER JOIN LANGUAGE L ON CL.language_id = L.language_id ) L ON C.customer_id = L.customer_id "
				+ "ORDER BY C.customer_id";
		return getCustomersBySql(sql);
	}

	public List<Customer> getCustomersBySql(String sql) {
		List<Customer> customers = new ArrayList<Customer>();
		List<Language> languages = new ArrayList<Language>();
		List<Map<String, Object>> rows;
		try {
		rows = jdbcTemplate.queryForList(sql);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		Customer customer = new Customer();
		int currentId = 0;

		for (Map<String, Object> row : rows) {
			if (currentId == (Integer) row.get("customer_id")) {
				if (row.get("language_id") != null) {
					languages.add(new Language((Integer) (row.get("language_id")), (String) row.get("language")));
				}
			} else {
				if (currentId != 0) {
					customer.setLanguages(languages);
					customers.add(customer);
				}
				currentId = (Integer) row.get("customer_id");
				customer = new Customer();
				languages = new ArrayList<Language>();
				customer.setId((Integer) (row.get("customer_id")));
				customer.setName((String) row.get("name"));
				customer.setBirthday((Date) row.get("birthday"));
				customer.setAddress((String) row.get("address"));
				customer.setGender((String) row.get("gender"));
				customer.setSchool((String) row.get("school"));
				customer.setSchoolYear((Integer) row.get("school_year"));
				if (row.get("language_id") != null) {
					languages.add(new Language((Integer) (row.get("language_id")), (String) row.get("language")));
				}
			}
		}
		customer.setLanguages(languages);
		customers.add(customer);
		return customers;
	}

	@Override
	public Integer getNextId() {
		String sql = "Select nextval(pg_get_serial_sequence('customer', 'customer_id'))";
		return jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
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
}