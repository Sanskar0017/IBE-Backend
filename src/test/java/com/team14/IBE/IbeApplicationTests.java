package com.team14.IBE;

import com.team14.IBE.Service.GraphQLTestingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GraphQLTestingServiceTest {

	@Autowired
	private GraphQLTestingService graphQLTestingService;

	@MockBean
	private RestTemplate restTemplate;

	private static final String ROOMS_DATA = "{ listRooms {room_id room_number } }";
	private static final String API_KEY = "da2-fakeApiId123456";

	@Test
	void testGetRooms() {
		// Mock response from the REST API
		String responseBody = "{\"data\":{\"listRooms\":[{\"room_id\":1,\"room_number\":1},{\"room_id\":2,\"room_number\":2},{\"room_id\":3,\"room_number\":3},{\"room_id\":4,\"room_number\":4},{\"room_id\":5,\"room_number\":5},{\"room_id\":6,\"room_number\":6},{\"room_id\":7,\"room_number\":7},{\"room_id\":8,\"room_number\":8},{\"room_id\":9,\"room_number\":9},{\"room_id\":10,\"room_number\":10},{\"room_id\":11,\"room_number\":11},{\"room_id\":12,\"room_number\":12},{\"room_id\":13,\"room_number\":13},{\"room_id\":14,\"room_number\":14},{\"room_id\":15,\"room_number\":15},{\"room_id\":16,\"room_number\":16},{\"room_id\":17,\"room_number\":17},{\"room_id\":18,\"room_number\":18},{\"room_id\":19,\"room_number\":19},{\"room_id\":20,\"room_number\":20},{\"room_id\":21,\"room_number\":21},{\"room_id\":22,\"room_number\":22},{\"room_id\":23,\"room_number\":23},{\"room_id\":24,\"room_number\":24},{\"room_id\":25,\"room_number\":25},{\"room_id\":26,\"room_number\":26},{\"room_id\":27,\"room_number\":27},{\"room_id\":28,\"room_number\":28},{\"room_id\":29,\"room_number\":29},{\"room_id\":30,\"room_number\":30},{\"room_id\":31,\"room_number\":31},{\"room_id\":32,\"room_number\":32},{\"room_id\":33,\"room_number\":33},{\"room_id\":34,\"room_number\":34},{\"room_id\":35,\"room_number\":35},{\"room_id\":36,\"room_number\":36},{\"room_id\":37,\"room_number\":37},{\"room_id\":38,\"room_number\":38},{\"room_id\":39,\"room_number\":39},{\"room_id\":40,\"room_number\":40},{\"room_id\":41,\"room_number\":41},{\"room_id\":42,\"room_number\":42},{\"room_id\":43,\"room_number\":43},{\"room_id\":44,\"room_number\":44},{\"room_id\":45,\"room_number\":45},{\"room_id\":46,\"room_number\":46},{\"room_id\":47,\"room_number\":47},{\"room_id\":48,\"room_number\":48},{\"room_id\":49,\"room_number\":49},{\"room_id\":50,\"room_number\":50}]}}";
		ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

		// Mock the RestTemplate's exchange method
		when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
				.thenReturn(mockResponse);

		ResponseEntity<String> response = graphQLTestingService.getRooms();

		HttpHeaders expectedHeaders = new HttpHeaders();
		expectedHeaders.setContentType(MediaType.APPLICATION_JSON);
		expectedHeaders.set("x-api-key", API_KEY);

		HttpEntity<String> expectedEntity = new HttpEntity<>("{ \"query\": \"" + ROOMS_DATA + "\" }", expectedHeaders);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseBody, response.getBody());
	}
}
