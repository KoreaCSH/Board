package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import command.LoginCommand;
import command.LoginInfo;
import exception.WrongIdPasswordException;
import service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping
	public String handlerForm(LoginCommand loginCommand) {
		
		return "login/form";
	}
	
	@PostMapping
	public String handlerSubmit(LoginCommand loginCommand) {
		
		try {
			LoginInfo loginInfo = loginService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
		} catch(WrongIdPasswordException e) {
			return "login/form";
		}
		
		return "login/success";
	}
	
}
