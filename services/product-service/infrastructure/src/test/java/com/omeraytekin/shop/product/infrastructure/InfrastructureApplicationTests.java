package com.omeraytekin.shop.product.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class InfrastructureApplicationTests {

	@Test
	void contextLoads() {
	}

}
