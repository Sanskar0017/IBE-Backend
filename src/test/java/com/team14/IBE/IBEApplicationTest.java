package com.team14.IBE;

import com.team14.IBE.dto.TenantConfigDTOTest;
import com.team14.IBE.tests.LandingPageControllerTest;
import com.team14.IBE.tests.TenantConfigControllerTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan("com.team14.IBE")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class IBEApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void testLandingPageController() {
		LandingPageControllerTest landingPageControllerTest = new LandingPageControllerTest();
		landingPageControllerTest.setUp();
		landingPageControllerTest.testGetPropertyRates();
	}

	@Test
	@Order(2)
	void testTenantConfigController() throws Exception {
		TenantConfigControllerTest tenantConfigControllerTest = new TenantConfigControllerTest();
		tenantConfigControllerTest.setUp();
		tenantConfigControllerTest.testGetTenantConfig();
	}

	@Test
	@Order(3)
	void testTenantConfigDTO() {
		TenantConfigDTOTest tenantConfigDTOTest = new TenantConfigDTOTest();
		tenantConfigDTOTest.testTenantConfigDTO();
	}
}
