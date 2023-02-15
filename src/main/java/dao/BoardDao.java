package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dto.Board;

public class BoardDao {

	private JdbcTemplate jdbcTemplate;
	
	public BoardDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Board> selectAll() {
		
		List<Board> list = jdbcTemplate.query(
				"select board_id, title, email, content, uploaddate, hit, files, name from Member natural join Board", 
				(ResultSet rs, int rowNum) -> {
					Board board = new Board(
							rs.getString("title"),
							rs.getString("email"),
							rs.getString("content"),
							rs.getTimestamp("uploaddate").toLocalDateTime(),
							rs.getInt("hit"),
							rs.getString("files"));
					board.setBoard_id(rs.getLong("board_id"));
					board.setName(rs.getString("name"));
					
					return board;
				});
		
		return list;
	}

	public Board selectById(Long board_id) {
		
		List<Board> results = jdbcTemplate.query(
				"select board_id, title, email, content, uploaddate, hit, files, name from Member natural join Board where board_id=?",
				new RowMapper<Board>() {
					@Override
					public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
						Board board = new Board(
								rs.getString("title"),
								rs.getString("email"),
								rs.getString("content"),
								rs.getTimestamp("uploaddate").toLocalDateTime(),
								rs.getInt("hit"),
								rs.getString("files"));
						board.setBoard_id(rs.getLong("board_id"));
						board.setName(rs.getString("name"));
						
						return board;
					}
				},
				board_id);
		
		return results.isEmpty() ? null : results.get(0);
	}
 	
}
