package dao;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import dto.Comment;

public class CommentDao {

	private JdbcTemplate jdbcTemplate;
	
	public CommentDao(DataSource dataSoruce) {
		this.jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	public List<Comment> selectAllByBoardId(Long board_id) {
		
		List<Comment> list = jdbcTemplate.query(
				"select comment_id, board_id, email, comment_content, comment_uploaddate from Board_comment where board_id=? order by comment_uploaddate", 
				(ResultSet rs, int rowNum) -> {
					Comment comment = new Comment(
							rs.getLong("board_id"),
							rs.getString("email"),
							rs.getString("comment_content"),
							rs.getTimestamp("comment_uploaddate").toLocalDateTime());
					
					comment.setComment_id(rs.getLong("comment_id"));
					
					return comment;
				}, board_id);
		
		return list;
	}
	
}
