package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키없으면 접근금지
		HttpSession session = request.getSession();
		if (session == null) {
			WebUtil.redirect(request, response, request.getContextPath() + "/board?a=writeform");
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		// 로그인한 상태가 아니면 접근 금지!!!
		if (authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath() + "/board?a=writeform");
			return;
		}
		String group_no = request.getParameter("group_no");
		String order_no = request.getParameter("order_no");
		String depth = request.getParameter("depth");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		BoardVo vo = new BoardVo();

		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(authUser.getNo());
		System.out.println(vo);
		if ("".equals(group_no) || "".equals(order_no) || "".equals(depth)) {
			new BoardRepository().insert(vo);
			WebUtil.redirect(request, response, request.getContextPath() + "/board");
			return;
		}
		vo.setGroup_no(Long.parseLong(group_no));
		vo.setOrder_no(Long.parseLong(order_no));
		vo.setDepth(Long.parseLong(depth));

		new BoardRepository().insert(vo);
		WebUtil.redirect(request, response, request.getContextPath() + "/board");
	}
}