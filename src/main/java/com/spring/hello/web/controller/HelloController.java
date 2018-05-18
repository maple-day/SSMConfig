package com.spring.hello.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@Value("${db.driverClassName}")
	private String dircerClassName;
	
	@Value("${db.url}")
	private String url;

	@RequestMapping(value = "/hello", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String hello(HttpServletRequest request) {
		Object date = request.getAttribute("date");
		return "hello spring config" + date.toString() + "啥的断点";
	}

	@RequestMapping(value = "/toHello")
	@ResponseBody
	public String toHello(HttpServletRequest request) {
		Object date = request.getAttribute("date");
		return "hello spring config" + date.toString() + "啥的断点";
	}

	@RequestMapping("index")
	public String index() {
		System.out.println("-----HelloController--index---");
		return "index";
	}

	@RequestMapping("url")
	@ResponseBody
	public String url(HttpServletRequest request) {
		return "url：" + url;
	}

	@RequestMapping("error")
	@ResponseBody
	public String error(HttpServletRequest request) {
		Object date = request.getAttribute("date");
		int a = 4 / 0;
		return "hello spring config" + date.toString() + a;
	}

	@RequestMapping("toError")
	@ResponseBody
	public String toError(HttpServletRequest request) {
		Object date = request.getAttribute("date");
		int[] a = new int[5];
		return "hello spring config" + date.toString() + a[6];
	}

}
