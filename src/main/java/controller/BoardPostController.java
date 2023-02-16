package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import command.LoginInfo;
import command.PostCommand;
import service.PostService;
import validator.PostCommandValidator;

@Controller
@RequestMapping("/board/post")
public class BoardPostController {

	private PostService postService;
	
	public BoardPostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public String form(PostCommand postCommand) {
		
		return "board/post";
	}
	
	@PostMapping
	public String submit(PostCommand postCommand, Errors errors, HttpSession session) {
		
		new PostCommandValidator().validate(postCommand, errors);
		if(errors.hasErrors()) {
			return "board/post";
		}
		
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		String email = loginInfo.getEmail();
		
		postService.post(postCommand, email);
		
		return "redirect:/board";
	}
	
}
