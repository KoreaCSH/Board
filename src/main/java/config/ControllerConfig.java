package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.BoardDetailController;
import controller.BoardListController;
import controller.BoardPostController;
import controller.ChangePasswordController;
import controller.LoginController;
import controller.LogoutController;
import controller.RegisterController;
import dao.BoardDao;
import dao.CommentDao;
import service.ChangePasswordService;
import service.LoginService;
import service.PostService;
import service.RegisterService;

@Configuration
public class ControllerConfig {

	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ChangePasswordService changePasswordService;
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentDao commentDao;
	
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
	
	@Bean
	public BoardListController boardListController() {
		return new BoardListController(boardDao);
	}
	
	@Bean
	public BoardDetailController boardDetailController() {
		return new BoardDetailController(boardDao, commentDao);
	}
	
	@Bean
	public BoardPostController boardPostController() {
		return new BoardPostController(postService);
	}
	
}
