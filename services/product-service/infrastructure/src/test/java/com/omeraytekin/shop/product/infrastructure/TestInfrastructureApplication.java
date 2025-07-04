package com.omeraytekin.shop.product.infrastructure;

import org.springframework.boot.SpringApplication;

public class TestInfrastructureApplication {

	public static void main(String[] args) {
		SpringApplication.from(InfrastructureApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
