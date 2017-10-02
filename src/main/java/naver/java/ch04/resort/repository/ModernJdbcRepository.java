package naver.java.ch04.resort.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import naver.java.ch04.resort.domain.Guest;

public class ModernJdbcRepository implements GuestRepository {
	private JdbcTemplate jdbc;
	public ModernJdbcRepository(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	private static final String SELECT_ALL = "SELECT name, grade, company FROM guest";
	private static final String DELETE_ALL = "DELETE FROM guest";
	
	@Override
	public void save(Guest... guests) {
		SimpleJdbcInsert insertStmt = new SimpleJdbcInsert(jdbc).withTableName("guest");
		for (Guest guest : guests) {
			BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(guest);
			insertStmt.execute(params);
		}
	}

	@Override
	public List<Guest> findAll() {
		return jdbc.query(SELECT_ALL, (rs, rowNum) -> new Guest(
								rs.getInt("id"),
								rs.getInt("grade"),
								rs.getString("name"),
								rs.getString("company")
				)
		);
	}

	@Override
	public void deleteAll() {
		jdbc.update(DELETE_ALL);
	}

}
