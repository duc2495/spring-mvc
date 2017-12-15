package hrs.training.springmvcex1.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.util.StringUtils;

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
		String sql = "INSERT INTO Customer (name, birthday, address, gender, school, school_year, languages)"
				+ "VALUES ( :name, :birthday, :address, :gender, :school, :schoolYear, :languages)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(customer));
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

	private SqlParameterSource getSqlParameterByModel(Customer customer) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		// paramSource.addValue("id", customer.getId());
		paramSource.addValue("name", customer.getName());
		paramSource.addValue("birthday", customer.getBirthday());
		paramSource.addValue("address", customer.getAddress());
		paramSource.addValue("gender", customer.getGender());
		paramSource.addValue("school", customer.getSchool());
		paramSource.addValue("schoolYear", customer.getSchoolYear());
		paramSource.addValue("languages", convertListToDelimitedString(customer.getLanguages()));

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
			aCustomer.setLanguages(convertDelimitedStringToList(rs.getString("languages")));
			return aCustomer;
		}
	}

	private static List<String> convertDelimitedStringToList(String delimitedString) {

		List<String> result = new ArrayList<String>();

		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;

	}

	private String convertListToDelimitedString(List<String> list) {

		String result = "";
		if (list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		return result;

	}

}