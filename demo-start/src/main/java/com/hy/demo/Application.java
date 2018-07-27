package com.hy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"com.hy.demo"})
//@EnableJpaRepositories(basePackages = {"com.hy.demo.dal.dao"})
//@EntityScan(basePackages = {"com.hy.demo.dal.dataobject"})
//如果本类所在位置不在当前根包下，则必须启用@Enalble*
@ServletComponentScan
@EnableWebMvc
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
