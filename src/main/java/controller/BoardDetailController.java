package controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import command.CommentCommand;
import command.LoginInfo;
import dao.BoardDao;
import dao.CommentDao;
import dto.Board;
import dto.Comment;
import exception.BoardNotFoundException;
import validator.CommentCommandValidator;

@Controller
public class BoardDetailController {

	private BoardDao boardDao;
	private CommentDao commentDao;
	
	public BoardDetailController(BoardDao boardDao, CommentDao commentDao) {
		this.boardDao = boardDao;
		this.commentDao = commentDao;
	}
	
	// 매핑 애노테이션 매서드에 @Transactional 설정해도 무방한가?
	// MemberConfig에 EnableTransactionManagement 애노테이션이 있으므로 service 클래스를 만든 후
	// 해당 Config에 service를 @Bean으로 등록하는 것이 올바른 방법일 듯 하다.
	@GetMapping("/board/{board_id}")
	@Transactional
	public String detail(@PathVariable("board_id") Long board_id, Model model,
			@ModelAttribute("commentCommand") CommentCommand commentCommand) {
		
		Board board = boardDao.selectById(board_id);
		if(board == null) {
			throw new BoardNotFoundException();
		}
		model.addAttribute("board", board);
		
		List<Comment> comment_list = commentDao.selectAllByBoardId(board_id);
		model.addAttribute("comment_list", comment_list);
		
		boardDao.updateHit(board);
		
		return "board/detail";
	}
	
	@PostMapping("/board/{board_id}")
	public String comment(@PathVariable("board_id") Long board_id, 
			@ModelAttribute("commentCommand") CommentCommand commentCommand,
			Errors errors,
			Model model,
			HttpSession session) {
		
		Board board = boardDao.selectById(board_id);
		if(board == null) {
			throw new BoardNotFoundException();
		}
		model.addAttribute("board", board);
		
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		if(loginInfo == null) {
			return "redirect:/login";
		}
		String email = loginInfo.getEmail();
		
		new CommentCommandValidator().validate(commentCommand, errors);
		if(errors.hasErrors()) {
			return "board/detail";
		}
		
		Comment newComment = new Comment(
				board_id,
				email,
				commentCommand.getComment_content(),
				LocalDateTime.now());
		
		boardDao.insert_comment(newComment);
		
		List<Comment> comment_list = commentDao.selectAllByBoardId(board_id);
		model.addAttribute("comment_list", comment_list);
		
		return "board/detail";
	}
	
	@ExceptionHandler(BoardNotFoundException.class)
	public String handlerBoardNotFoundException() {
		return "board/noBoard";
	}
	
}
