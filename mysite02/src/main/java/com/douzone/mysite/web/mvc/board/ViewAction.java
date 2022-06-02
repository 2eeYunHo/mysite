package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String COOKIE_NAME = null;
		int vistit = 0;
		String no = request.getParameter("no");
		List<BoardVo> list = new BoardRepository().findByNo(Integer.parseInt(no));

		request.setAttribute("list", list);

		COOKIE_NAME = "visitor" + Integer.parseInt(no);

		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (COOKIE_NAME.equals(cookie.getName())) {
					vistit = Integer.parseInt(cookie.getValue());
				}
			}
		}

		if (vistit == 0) {
			vistit = 1;

			Cookie cookie = new Cookie(COOKIE_NAME, String.valueOf(vistit));
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(60 * 60 * 24); // 60초60번24번 조회수 쿨타임 (1일)
			response.addCookie(cookie);

			new BoardRepository().HitUp(Integer.parseInt(no));
		}

		WebUtil.forward(request, response, "board/view");
	}
}
