package service;

import java.time.LocalDateTime;

import command.RegisterCommand;
import dao.MemberDao;
import dto.Member;
import exception.DuplicateMemberException;

public class RegisterService {

	private MemberDao memberDao;
	
	public RegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Long regist(RegisterCommand registerCommand) {
		Member member = memberDao.selectByEmail(registerCommand.getEmail());
		
		if(member != null) {
			throw new DuplicateMemberException(registerCommand.getEmail() +"는 이미 가입된 이메일입니다.");
		}
		
		Member newMember = new Member(registerCommand.getEmail(), registerCommand.getName(), registerCommand.getSex(), registerCommand.getBirthdate(), registerCommand.getPassword(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();

	}
	
}
