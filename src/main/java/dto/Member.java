package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Member {

	private Long id;
	private String email;
	private String name;
	private String sex;
	private String birthDate;
	private String password;
	private LocalDateTime regdate;
	
	public Member() {
		
	}
	
	public Member(String email, String name, String sex, String birthDate, String password, LocalDateTime regdate) {
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
		this.password = password;
		this.regdate = regdate;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getRegdate() {
		return regdate;
	}
	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}
	
}
