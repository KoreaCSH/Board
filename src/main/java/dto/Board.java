package dto;

import java.time.LocalDateTime;

public class Board {

	private Long board_id;
	private String title;
	private String email;
	private String content;
	private LocalDateTime uploaddate;
	private int hit;
	private String files;
	
	public Board() {

	}
	
	public Board(String title, String email, String content, LocalDateTime uploaddate, int hit, String files) {
		this.title = title;
		this.email = email;
		this.content = content;
		this.uploaddate = uploaddate;
		this.hit = hit;
		this.files = files;
	}

	public Long getBoard_id() {
		return board_id;
	}
	public void setBoard_id(Long board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(LocalDateTime uploaddate) {
		this.uploaddate = uploaddate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	
}
