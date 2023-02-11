package service;

import command.LoginInfo;
import dao.MemberDao;
import dto.Member;
import exception.WrongIdPasswordException;

public class LoginService {

	private MemberDao memberDao;
	
	public LoginService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public LoginInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		
		if(member == null) {
			throw new WrongIdPasswordException();
		}
		if(!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		
		return new LoginInfo(member.getId(), member.getEmail(), member.getName());
	}
	
}
