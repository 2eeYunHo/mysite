package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String hanlerException(Model model, Exception e) {
		//1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));//이거로 로깅함 && 객체로 받으려면 파이프로 연결해야함
		e.printStackTrace();
		
		//2. 사과 페이지(종료)요청에의한
		model.addAttribute("exception", errors.toString());
		return "error/exception";
	}
}