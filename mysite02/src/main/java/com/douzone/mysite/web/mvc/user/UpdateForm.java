package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class UpdateForm implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//접근제어(Access Control)
		////////////////////////////////////////////////
		HttpSession session = request.getSession();
		//이미 세션이 있다면 그 세션을 돌려주고, 세션이 없으면 새로운 세션을 생성한다.
		if (session == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			//세션없이 접속하는걸 방지
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		//authUser가 있다면 authUser값을 돌려주고, 없으면 새로 생성
		if(authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			//authUser없이 접속하는것을 방지
			return;
		}
		////////////////////////////////////////////////
		UserVo userVo = new UserRepository().findByNo(authUser.getNo());
		request.setAttribute("userVo", userVo);
		WebUtil.forward(request, response, "user/updateform");
	}

}
