package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class Delete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//.1 sql 작업
		Long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		new GuestBookRepository().delete(vo);
		
		//.2 
		WebUtil.redirect(request, response, request.getContextPath() + "/guestbook");
		// list -add(화면 없으니 리다이렉트) - deleteform - delete(화면 없으니 리다이렉트)
	}
 
}
