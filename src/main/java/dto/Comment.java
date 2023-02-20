package dto;

import java.time.LocalDateTime;

public class Comment {

	private Long comment_id;
	private Long board_id;
	private String email;
	private String comment_content;
	private LocalDateTime comment_uploaddate;
	
	public Comment() {

	}

	public Comment(Long board_id, String email, String comment_content,
			LocalDateTime comment_uploaddate) {
		
		this.board_id = board_id;
		this.email = email;
		this.comment_content = comment_content;
		this.comment_uploaddate = comment_uploaddate;
	}

	public Long getComment_id() {
		return comment_id;
	}

	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}

	public Long getBoard_id() {
		return board_id;
	}

	public void setBoard_id(Long board_id) {
		this.board_id = board_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public LocalDateTime getComment_uploaddate() {
		return comment_uploaddate;
	}

	public void setComment_uploaddate(LocalDateTime comment_uploaddate) {
		this.comment_uploaddate = comment_uploaddate;
	}
	
}
