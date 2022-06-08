package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";// 응답처리
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, UserVo vo, Model model) {
		UserVo authUser = userService.getUser(vo);
		model.addAttribute("result", "fail");
		model.addAttribute("email", vo.getEmail());
		//System.out.println(authUser);// 로그인되는지 확인작업
		if(authUser ==null) {
			return "user/login";
		}
		// 인증 처리
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	@RequestMapping("/logout")
	public	String logout(HttpSession session) {
		 session.removeAttribute("authUser");
		 session.invalidate();
		 return "redirect:/";
	}
	
	
	@RequestMapping(value = "/update",method=RequestMethod.GET)
	public String update(HttpSession session, Model model) { 
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//------------------------------------------------위 if 접근제어
		
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		
		model.addAttribute("userVo",userVo);
		return "user/update";
	}
	@RequestMapping(value ="/update", method= RequestMethod.POST)
	//접근제어 (Access Controller)
	public String update(HttpSession session, UserVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//-------------------------------------------------------
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());
		System.out.println(vo);
		return "redirect:/user/update";
	}
}