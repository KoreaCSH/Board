package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.LoginController;
import controller.RegisterController;
import service.LoginService;
import service.RegisterService;

@Configuration
public class ControllerConfig {

	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private LoginService loginService;
	
	@Bean
	public RegisterController registerController() {
		return new RegisterController(registerService);
	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController(loginService);
	}
	
}
