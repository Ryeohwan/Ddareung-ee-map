package com.bicycle.map.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bicycle.map.member.dto.Member;


@Mapper
public interface MemberMapper {
	public String login(Member m);
	public int join(Member m);
	public void update(Member m);
	public int idCheck(String id);
	public Member getMember(String id);
}
