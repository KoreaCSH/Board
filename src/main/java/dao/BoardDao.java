package dao;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import dto.Board;

public class BoardDao {

	private JdbcTemplate jdbcTemplate;
	
	public BoardDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Board> selectAll() {
		
		List<Board> list = jdbcTemplate.query(
				"select * from Board", 
				(ResultSet rs, int rowNum) -> {
					Board board = new Board(
							rs.getString("title"),
							rs.getString("email"),
							rs.getString("content"),
							rs.getTimestamp("uploaddate").toLocalDateTime(),
							rs.getInt("hit"),
							rs.getString("files"));
					board.setBoard_id(rs.getLong("board_id"));
					
					return board;
				});
		
		return list;
	}
 	
}
