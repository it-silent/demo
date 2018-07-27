package com.hy.demo.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * ApplicationTestForService
 *          
 *
 * @author  Yu.HaiYang
 * @date    2018/7/20
 */
@SpringBootApplication(scanBasePackages = {"com.hy.demo"})
@EnableJpaRepositories(basePackages = {"com.hy.demo.dal.repositories"})
@EntityScan(basePackages = {"com.hy.demo.dal.dataobject"})
@PropertySource(value = "classpath:application.yml")
public class ApplicationTestForService {
}
