package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import dto.Member;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		
		List<Member> results = jdbcTemplate.query(
				"select * from Member where email=?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("email"),
								rs.getString("name"),
								rs.getString("sex"),
								rs.getString("birthdate"),
								rs.getString("password"),
								rs.getTimestamp("regdate").toLocalDateTime());
						member.setId(rs.getLong("id"));
						return member;
					}
				},
				email);
		
		return results.isEmpty() ? null : results.get(0);
		
	}
	
	public void insert(Member member) {
		// 자동으로 생성된 key값을 구하기 위해 keyHolder 사용
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into Member (email, name, sex, birthdate, password, regdate) values (?,?,?,?,?,?)",
						new String[] {"id"});
				
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getName());
				pstmt.setString(3, member.getSex());
				pstmt.setString(4, member.getBirthDate());
				pstmt.setString(5, member.getPassword());
				pstmt.setTimestamp(6, Timestamp.valueOf(member.getRegdate()));
				
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
}
