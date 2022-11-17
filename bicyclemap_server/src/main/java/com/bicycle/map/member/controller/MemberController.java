package com.bicycle.map.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bicycle.map.member.dto.Member;
import com.bicycle.map.member.service.MemberService;



@Controller
@RequestMapping("/member")
public class MemberController {
	

	@Autowired
	MemberService memberService;
	
	@PostMapping("/login")
	@ResponseBody
	public String login(Member m, HttpServletRequest request) {
		//Map<String, String> map = new HashMap<>();
		System.out.println(m);
		String name = memberService.login(m);
		if(name!=null) {
			m.setName(name);
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			return name;
		}
		return null;
	}
	
	@PostMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "로그아웃 되었습니다.";
    }
 
 
	
	@GetMapping("/update")
	public String update(@ModelAttribute("member") Member member,@RequestParam("id") String id, Model model) {
		Member m = memberService.getMember(id);
		model.addAttribute("member",m);
		System.out.println("update get mappping 왔다");
		return "memberUpdate";
	}
		
	@PostMapping("/update")
	@ResponseBody
	public void update(Member m) {
		memberService.update(m);
	}
	
	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(@PathVariable("userid") String id) throws Exception {
		int cnt = memberService.idCheck(id);
		return cnt + "";
	}
	
	
	@GetMapping("/join")
	public String join() {
		System.out.println("join get mappping 왔다");
		return "signUp";
	}
	
	@PostMapping("/join")
	@ResponseBody
	public String join(Member m) {
		System.out.println("join post mapping 굿");
		int i =memberService.join(m);
		if(i>0) {
			System.out.println(m);
			return "regist ok";
		}else {
			return "regist fail";
		}
		
	}
}
