package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import dto.Board;
import dto.Comment;

public class BoardDao {

	private JdbcTemplate jdbcTemplate;
	
	public BoardDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Board> selectAll() {
		
		List<Board> list = jdbcTemplate.query(
				"select board_id, title, email, content, uploaddate, hit, files, name from Member natural join Board order by uploaddate desc", 
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
	
	public void insert(Board board) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into Board (title, email, content, uploaddate, hit, files) values (?,?,?,?,?,?)",
						new String[] {"board_id"});
				
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getEmail());
				pstmt.setString(3, board.getContent());
				pstmt.setTimestamp(4, Timestamp.valueOf(board.getUploaddate()));
				pstmt.setInt(5, board.getHit());
				pstmt.setString(6, board.getFiles());
				
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		board.setBoard_id(keyValue.longValue());
	}
	
	public void insert_comment(Comment comment) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into Board_comment (board_id, email, comment_content, comment_uploaddate) values (?,?,?,?)",
						new String[] {"comment_id"});
				
				pstmt.setLong(1, comment.getBoard_id());
				pstmt.setString(2, comment.getEmail());
				pstmt.setString(3, comment.getComment_content());
				pstmt.setTimestamp(4, Timestamp.valueOf(comment.getComment_uploaddate()));
				
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		comment.setComment_id(keyValue.longValue());
	}
	
	public void updateHit(Board board) {
		jdbcTemplate.update(
				"UPDATE Board SET hit = hit + 1 WHERE board_id=?", board.getBoard_id());
	}
 	
}
