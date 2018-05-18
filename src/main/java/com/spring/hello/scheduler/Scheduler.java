package com.spring.hello.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.spring.hello.domain.entiy.User;
import com.spring.hello.service.HelloService;

@EnableScheduling
@Component
@Configuration
public class Scheduler {

	@Autowired
	private HelloService helloService;
	
	@Scheduled(cron="0/10 * * * * ? ")
	public void helloSchedule() {
		List<User> userList = helloService.getUserList();
		System.err.println("----Scheduler------"+userList.toString());
	}
	
	/**
	 * 配置一个默认，要不然spring 会报DEBUG
	 */
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		return scheduler;
	}
	
}
