package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import command.ChangePasswordCommand;
import command.LoginInfo;
import exception.WrongIdPasswordException;
import service.ChangePasswordService;
import validator.ChangePasswordCommandValidator;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePasswordController {

	private ChangePasswordService changePasswordService;
	
	public ChangePasswordController(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}
	
	@GetMapping
	public String handlerForm(@ModelAttribute("changePasswordCommand") ChangePasswordCommand changePasswordCommand) {
		return "edit/changePwdForm";
	}
	
	@PostMapping
	public String handlerSubmit(@ModelAttribute("changePasswordCommand") ChangePasswordCommand changePasswordCommand, 
			Errors errors, HttpSession session) {
		
		new ChangePasswordCommandValidator().validate(changePasswordCommand, errors);
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		try {
			LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo"); 
			
			changePasswordService
			.changePassword(loginInfo.getEmail(), 
					changePasswordCommand.getCurrentPassword(), 
					changePasswordCommand.getNewPassword());
			
			return "edit/changePwdsuccess";
		} catch(WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "notMatching");;
			return "edit/changePwdForm";
		}
	}
	
}
