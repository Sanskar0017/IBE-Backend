package com.team14.IBE.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GraphQLTestingService {
    private static final String ROOMS_DATA = "{ listRooms {room_id room_number } }";
    @Value("${api.key}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();
    public ResponseEntity<String> getRooms(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);
        String requestBody = "{ \"query\": \"" + ROOMS_DATA + "\" }";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        return restTemplate.exchange("http://localhost:4000/graphql", HttpMethod.POST, requestEntity, String.class);
    }
}
