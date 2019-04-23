package com.sy.repository;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages={"sy"})
@ComponentScan(basePackages={"sy"})
@EnableDiscoveryClient // 提供服务
@SpringBootApplication
public class RepositoryApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(RepositoryApplication.class, args);
	}
	@Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}