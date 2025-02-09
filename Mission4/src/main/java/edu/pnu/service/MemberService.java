package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	private MemberDAO memberDao;
	private LogDao logDao;
	
	public MemberService() {
		memberDao = new MemberDAO();
		logDao = new LogDao();
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getAllMember() {
		Map<String, Object> map = memberDao.getAllMember();
		logDao.addLog("Get", (String) map.get("sqlstring"), (boolean) map.get("success"));
		return (List<MemberVO>) map.get("list");
	}
	
	public MemberVO getMemberById(Integer id) {
		Map<String, Object> map = memberDao.getMemberById(id);
		logDao.addLog("Get", (String) map.get("sqlstring"), (boolean) map.get("success"));
		return (MemberVO) map.get("member");
	}
	
	public MemberVO addMemberVO(MemberVO memberVO) {
		Map<String, Object> map = memberDao.addMemberVO(memberVO);
		logDao.addLog("Post", (String) map.get("sqlstring"), (boolean) map.get("success"));
		return (MemberVO) map.get("member");
	}
	
	public int updateMembers(MemberVO memberVO) {
		Map<String, Object> map = memberDao.updateMembers(memberVO);
		logDao.addLog("Put", (String) map.get("sqlstring"), (boolean) map.get("success"));
		return (int) map.get("result");
	}
	
	public int removeMember(Integer id) {
		Map<String, Object> map = memberDao.removeMember(id);
		logDao.addLog("Delete", (String) map.get("sqlstring"), (boolean) map.get("success"));
		return (int) map.get("result");
	}
}
