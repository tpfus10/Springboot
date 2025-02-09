package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberservice;
	
	public MemberController() {
		System.out.println("MemberController 실행");
	}
	
	@GetMapping({"/member/{id}", "/member"})
	public ResponseEntity<?> getMember(@PathVariable(required = false) Integer id) {
		if (id == null)
			return ResponseEntity
				.ok(memberservice.getAllMember());
		
		return ResponseEntity
				.ok(memberservice.getMemberById(id));
	}
	
	@PostMapping("/member")
	public MemberVO addMemberVO(MemberVO memberVO) {
		MemberVO member = memberservice.addMemberVO(memberVO);
		System.out.println(member);
		return member;
	}
	
//	@PostMapping("/member")
//	public int InsertLog()
	
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		return memberservice.updateMembers(memberVO);
	}
	
	@DeleteMapping("/member/{id}") 
	public int removeMember(@PathVariable Integer id) {
		if(id == null)
			return 0;
		return memberservice.removeMember(id);
	}
}
