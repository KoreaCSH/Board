package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.BoardDao;
import dto.Board;

@Controller
public class BoardDetailController {

	private BoardDao boardDao;
	
	public BoardDetailController(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@GetMapping("/board/{board_id}")
	public String detail(@PathVariable("board_id") Long board_id, Model model) {
		
		Board board = boardDao.selectById(board_id);
		model.addAttribute("board", board);
		
		return "board/detail";
	}
	
}
