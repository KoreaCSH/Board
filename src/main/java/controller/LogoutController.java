package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		// Controller 없는 url로 가기 위해서는 redirect를 사용해야 한다.
		return "redirect:/main";
	}
	
}
