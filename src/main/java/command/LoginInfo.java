package command;

public class LoginInfo {

	private Long id;
	private String email;
	private String name;
	
	public LoginInfo() {
	
	}
	
	public LoginInfo(Long id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	
}
