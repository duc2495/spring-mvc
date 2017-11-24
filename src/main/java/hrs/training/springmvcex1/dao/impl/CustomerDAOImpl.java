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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.StringUtils;

import hrs.training.springmvcex1.dao.CustomerDAO;
import hrs.training.springmvcex1.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public CustomerDAOImpl(DataSource dataSource) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void insert(Customer customer) {
		String sql = "INSERT INTO surveydb.customer (name, birthday, address, sex, school, year, subjects)"
				+ "VALUES ( :name, :birthday, :address, :sex, :school, :year, :subjects)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(customer));
	}

	@Override
	public Customer findByCustomerId(int custId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", custId);

		String sql = "SELECT * FROM surveydb.customer WHERE id=:id";

		Customer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new CustomerMapper());
		} catch (EmptyResultDataAccessException e) {
			//
		}
		return result;
	}

	@Override
	public List<Customer> list() {
		String sql = "SELECT * FROM surveydb.customer";
		List<Customer> listCustomer = namedParameterJdbcTemplate.query(sql, new CustomerMapper());
		return listCustomer;
	}

	private SqlParameterSource getSqlParameterByModel(Customer customer) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		// paramSource.addValue("id", customer.getId());
		paramSource.addValue("name", customer.getName());
		paramSource.addValue("birthday", customer.getBirthday());
		paramSource.addValue("address", customer.getAddress());
		paramSource.addValue("sex", customer.getSex());
		paramSource.addValue("school", customer.getSchool());
		paramSource.addValue("year", customer.getYear());
		paramSource.addValue("subjects", convertListToDelimitedString(customer.getSubjects()));

		return paramSource;
	}

	private static final class CustomerMapper implements RowMapper<Customer> {

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer aCustomer = new Customer();
			aCustomer.setId(rs.getInt("id"));
			aCustomer.setName(rs.getString("name"));
			aCustomer.setBirthday(rs.getDate("birthday"));
			aCustomer.setAddress(rs.getString("address"));
			aCustomer.setSex(rs.getString("sex"));
			aCustomer.setSchool(rs.getString("school"));
			aCustomer.setYear(rs.getInt("year"));
			aCustomer.setSubjects(convertDelimitedStringToList(rs.getString("subjects")));
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