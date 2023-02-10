package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import command.RegisterCommand;
import exception.DuplicateMemberException;
import service.RegisterService;
import validator.RegisterCommandValidator;

@Controller
public class RegisterController {

	private RegisterService registerService;
	
	public RegisterController(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@GetMapping("/register/terms")
	public String handlerterms() {
		return "register/terms";
	}
	
	@PostMapping("/register/form")
	public String handlerForm(HttpServletRequest request) {
		String agree = request.getParameter("agree");
		if(agree == null || !agree.equals("true")) {
			return "register/terms";
		}
		return "register/form";
	}
	
	@GetMapping("/register/form")
	public String handlerFormGet() {
		return "redirect:/register/terms";
	}
	
	@GetMapping("/register/success")
	public String handlerSuccessGet() {
		return "redirect:/register/terms";
	}
	
	@PostMapping("/register/success")
	public String handlerSuccess(@ModelAttribute("registerCommand") RegisterCommand registerCommand, Errors errors) {
		
		new RegisterCommandValidator().validate(registerCommand, errors);
		if(errors.hasErrors()) {
			return "register/form";
		}
		try {
			registerService.regist(registerCommand);
			return "register/success";			
		} catch(DuplicateMemberException e) {
			errors.rejectValue("email", "duplicate");
			return "register/form";
		}
	}
	
}
