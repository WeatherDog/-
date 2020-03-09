package com.zking;

import org.mybatis.spring.annotation.MapperScan;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zking.dao.mapper")
//@ComponentScan(basePackages = {"com.zking.mapper"})
public class SpringbootShiroRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootShiroRedisApplication.class, args);
	}
}
