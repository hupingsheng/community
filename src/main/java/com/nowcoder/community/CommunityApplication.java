package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class CommunityApplication {

	// ⽤来管理bean的⽣命周期的, 主要⽤来管理bean的初始化⽅法
	// 这个注解修饰的⽅法会在构造器调⽤完以后被执⾏
	@PostConstruct
	public void init() {
		// 解决netty启动冲突的问题
		// see Netty4Utils.setAvailableProcessors()
		System.setProperty("es.set.netty.runtime.available.processors", "false");
	}


	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
