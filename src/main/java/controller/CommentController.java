package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import command.CommentCommand;

@Controller
@RequestMapping("/board/comment")
public class CommentController {
	
	@PostMapping
	public String submit(@ModelAttribute("commentCommand") CommentCommand commentCommand) {
		
		return "redirect:/board";
	}
	
}
