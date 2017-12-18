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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import hrs.training.springmvcex1.dao.LanguageDAO;
import hrs.training.springmvcex1.model.Language;

public class LanguageDAOImpl implements LanguageDAO {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public LanguageDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate (dataSource);
	}

	@Override
	public void insert(Language language) {
		String sql = "INSERT INTO Language (name)" + "VALUES (?)";
		jdbcTemplate.update(sql, language.getName());

	}

	@Override
	public Language getLanguageById(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		String sql = "SELECT * FROM Language WHERE languge_id=:id";

		Language result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new LanguageMapper());
		} catch (EmptyResultDataAccessException e) {
			//
		}
		return result;
	}
	
	@Override
	public List<Language> listAll() {
		String sql = "SELECT * FROM Language";
		List<Language> listLanguage = jdbcTemplate.query(sql, new LanguageMapper());
		return listLanguage;
	}
	
	private static final class LanguageMapper implements RowMapper<Language> {

		public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
			Language language = new Language();
			language.setId(rs.getInt("language_id"));
			language.setName(rs.getString("name"));
			return language;
		}
	}

}
