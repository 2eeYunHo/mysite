package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		UserVo authUser = new UserRepository().findByEmailAndPassword(vo);
		if(authUser ==null) {
			// 로그인 실패시 처리
			System.out.println("로그인 실패");
			// request.setAttribute를 통해 실패시에만 출력값이 나오도록함
			request.setAttribute("result", "fail");
			request.setAttribute("email", email);

			WebUtil.forward(request, response, "user/loginform");
			return;
		}
		//실패시 처리가 위에서 일어났기 떄문에 아래는 성공처리
		System.out.println("로그인 성공");
	}

}
