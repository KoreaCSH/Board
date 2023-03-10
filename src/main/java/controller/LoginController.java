package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import command.LoginCommand;
import command.LoginInfo;
import exception.NoMemberException;
import exception.WrongIdPasswordException;
import service.LoginService;
import validator.LoginCommandValidator;

@Controller
@RequestMapping("/login")
public class LoginController {

	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping
	public String handlerForm(LoginCommand loginCommand,
								@CookieValue(value="REMEMBER", required = false) Cookie rememberCookie) {
		
		if(rememberCookie != null) {
			loginCommand.setEmail(rememberCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		
		return "login/form";
	}
	
	@PostMapping
	public String handlerSubmit(LoginCommand loginCommand, Errors errors, 
			HttpSession session, HttpServletResponse response) {
		
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/form";
		}
		
		try {
			LoginInfo loginInfo = loginService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			
			session.setAttribute("loginInfo", loginInfo);
			
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if(loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			
			return "login/success";
		} catch(NoMemberException e) { 
			errors.rejectValue("email", "nomember");
			return "login/form";
		} catch(WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/form";
		} 
	}
	
}
