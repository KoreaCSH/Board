package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.ChangePasswordController;
import controller.LoginController;
import controller.LogoutController;
import controller.RegisterController;
import service.ChangePasswordService;
import service.LoginService;
import service.RegisterService;

@Configuration
public class ControllerConfig {

	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ChangePasswordService changePasswordService;
	
	@Bean
	public RegisterController registerController() {
		return new RegisterController(registerService);
	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController(loginService);
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public ChangePasswordController changePasswordController() {
		return new ChangePasswordController(changePasswordService);
	}
	
}
