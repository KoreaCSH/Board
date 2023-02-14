package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BoardDao;
import dto.Board;

@Controller
@RequestMapping("/board")
public class BoardListController {

	private BoardDao boardDao;
	
	public BoardListController(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@GetMapping
	public String list(Model model) {
		List<Board> list = boardDao.selectAll();
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
}
