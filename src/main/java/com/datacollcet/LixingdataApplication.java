package com.datacollcet;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ImportResource(locations = {"classpath:application_legacy.xml"})
@MapperScan("com.datacollcet.dao")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableScheduling
//@Mapper("com.datacollcet.dao")
@Slf4j
public class LixingdataApplication {

	public static void main(String[] args) {

		SpringApplication.run(LixingdataApplication.class, args);
	}
}
