package service;

import java.time.LocalDateTime;

import command.PostCommand;
import dao.BoardDao;
import dto.Board;

public class PostService {

	private BoardDao boardDao;
	
	public PostService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public Long post(PostCommand postCommand, String email) {
		
		Board newboard = new Board(
				postCommand.getTitle(),
				email, 
				postCommand.getContent(), 
				LocalDateTime.now(), 
				0, 
				postCommand.getFiles());
		
		boardDao.insert(newboard);
		
		return newboard.getBoard_id();
	}
	
}
