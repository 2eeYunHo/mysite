package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@RequestMapping("/guestbook")
@Controller
public class GuestbookController {

	@Autowired // 묶는 작업을 와이어링이라하는데 그걸 해주는 어노테이션
	private GuestbookService guestBookService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(Model model) {
		List<GuestbookVo> list = guestBookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/index";
	}

	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST) // 이름은 꼭 일치 시키고 아래 내용에 required를 false를 줘도되지만
																			// 널값을 제외시키기 위해 트루를 주고 따로 선언한다.
	public String delete(@RequestParam(value = "password", required = true, defaultValue = "") String password,
			@PathVariable("no") Long no) {
		guestBookService.deleteMessage(no, password);
		System.out.println(no + password);

		return "redirect:/guestbook";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(GuestbookVo vo) {
		guestBookService.addMessage(vo);
		System.out.println(vo);
		return "redirect:/guestbook";// redirecet:/를 작성시에는 공백이 있으면 안됨
	}
	//상단의 모든 리퀘스트 핸들러에서 예외가 발생했을 때 실행될 수 있게끔
	@ExceptionHandler(Exception.class)
	public String handlerException() {
		 return "error/exception";
	}
}
