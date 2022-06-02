package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// .1 sql 작업
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");;
		
		long no = Integer.parseInt(request.getParameter("no"));
		
		new BoardRepository().delete(no, authUser.getNo());
		WebUtil.redirect(request, response, request.getContextPath() + "/board?a=board");
		// list -add(화면 없으니 리다이렉트) - deleteform - delete(화면 없으니 리다이렉트)
	}
}