package controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.BoardDao;
import dto.Board;
import exception.BoardNotFoundException;

@Controller
public class BoardDetailController {

	private BoardDao boardDao;
	
	public BoardDetailController(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	// 매핑 애노테이션 매서드에 @Transactional 설정해도 무방한가?
	// MemberConfig에 EnableTransactionManagement 애노테이션이 있으므로 service 클래스를 만든 후
	// 해당 Config에 service를 @Bean으로 등록하는 것이 올바른 방법일 듯 하다.
	@GetMapping("/board/{board_id}")
	@Transactional
	public String detail(@PathVariable("board_id") Long board_id, Model model) {
		
		Board board = boardDao.selectById(board_id);
		if(board == null) {
			throw new BoardNotFoundException();
		}
		boardDao.updateHit(board);
		
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@ExceptionHandler(BoardNotFoundException.class)
	public String handlerBoardNotFoundException() {
		return "board/noBoard";
	}
	
}
