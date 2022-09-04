package com.hms.roommanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(info=@Info(title="Room Management API",version="1.0",description="An API used for Room Management"))
public class RoomManagementApplication {

	@LoadBalanced
	public static void main(String[] args) {
		SpringApplication.run(RoomManagementApplication.class, args);
	}

}
