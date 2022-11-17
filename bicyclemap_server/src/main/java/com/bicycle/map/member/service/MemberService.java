package com.bicycle.map.member.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bicycle.map.member.dto.Member;
import com.bicycle.map.member.mapper.MemberMapper;



@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	public String login(Member m){
		return memberMapper.login(m);
	}
	
	public int join(Member m){
		return memberMapper.join(m);
	}

	public void update(Member m) {
		memberMapper.update(m);
		System.out.println(m.getPw());
	}

	public int idCheck(String id) {
		return memberMapper.idCheck(id);
	}

	public Member getMember(String id) {
		return memberMapper.getMember(id);
	}	
}
