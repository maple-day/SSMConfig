package com.spring.hello.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

	
	@ExceptionHandler(value=ArithmeticException.class)
	public String globalArithmeticException(ArithmeticException e) {
		System.out.println("ExceptionController-----globalException"+e.getMessage());
		System.out.println(e.toString());
		return "error";
	}
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public String globalException(Exception e) {
		System.out.println("ExceptionController-----globalException"+e.getMessage());
		System.out.println(e.toString());
		return "大手大脚";
	}
}
