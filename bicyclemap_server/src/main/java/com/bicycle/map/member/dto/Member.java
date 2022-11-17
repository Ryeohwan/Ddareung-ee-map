package com.bicycle.map.member.dto;

public class Member {
	private String name,id,pw;

	
	public Member() {
		super();
	}

	public Member(String name, String id, String pw) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null) {  // name에 널값이 들어가지 않게 합니다.
			this.name = name;
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id != null) {  // id에 널값이 들어가지 않게 합니다.
			this.id = id;
		}
		
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		if(pw != null) {  // 비밀번호에 널값이 들어가지 않게 합니다.
			this.pw = pw;
		}
		
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", id=" + id + ", pw=" + pw + "]";
	}
	
	
	
	
}