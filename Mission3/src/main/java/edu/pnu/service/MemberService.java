package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

public class MemberService {
	private MemberDAO dao;
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}
	
	public MemberVO getMemberById(Integer id) {
		return dao.getMemberById(id);
	}
	
	public MemberVO addMemberVO(MemberVO memberVO) {
		return dao.addMemberVO(memberVO);
	}
	
	public int updateMembers(MemberVO memberVO) {
		return dao.updateMembers(memberVO);
	}
	
	public int removeMember(Integer id) {
		return dao.removeMember(id);
	}
}
