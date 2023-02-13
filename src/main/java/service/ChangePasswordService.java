package service;

import org.springframework.transaction.annotation.Transactional;

import dao.MemberDao;
import dto.Member;
import exception.NoMemberException;

public class ChangePasswordService {

	private MemberDao memberDao;
	
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	// Spring이 자동으로 Transaction 관리
	@Transactional
	public void changePassword(String email, String oldpwd, String newpwd) {
		
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new NoMemberException();
		}
		member.changePassword(oldpwd, newpwd);
		
		memberDao.updatePassword(member);
	}
	
}
