package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.RegisterController;
import service.RegisterService;

@Configuration
public class ControllerConfig {

	@Autowired
	private RegisterService registerService;
	
	@Bean
	public RegisterController registerController() {
		return new RegisterController(registerService);
	}
	
}
