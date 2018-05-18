package com.spring.hello.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spring.hello.domain.entiy.User;
import com.spring.hello.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {


	@Override
	public List<User> getUserList() {
		ArrayList<User> arrayList = new ArrayList<>();
		User user;
		Date date;
		String password;
		for (int i = 0; i < 20; i++) {
			user = new User();
			date = new Date();
			password = UUID.randomUUID().toString().replaceAll("-", "");
			user.setId(i);
			user.setUserName("张三" + i);
			user.setCreateTime(date);
			user.setPassword(password);
			arrayList.add(user);
		}
		return arrayList;
	}

}
