package com.team14.IBE;

import com.team14.IBE.Service.GraphQLTestingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GraphQLTestingServiceTest {

	@Autowired
	private GraphQLTestingService graphQLTestingService;

	@MockBean
	private RestTemplate restTemplate;

	@Test
	void testSampleTest() {
		// Mocking the response from the REST API call
		String expectedResponse = "Hello This is Sanskar!!";
		when(restTemplate.getForEntity("/sampletest", String.class))
				.thenReturn(ResponseEntity.ok(expectedResponse));

		// Call the method being tested
		ResponseEntity<String> responseEntity = graphQLTestingService.sampleTest();

		// Verify the response
		assertEquals(expectedResponse, responseEntity.getBody());
	}
}
