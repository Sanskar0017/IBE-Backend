package com.team14.ibe.service;

import com.team14.ibe.dto.response.RoomRateResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RoomRateService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${api.key}")
    private String apiKey;
    @Value("${graphql.endpoint}")
    private String graphqlEndpoint;

    public List<RoomRateResponseDTO> getRoomRates(int roomTypeId, String startDate, String endDate) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);

        List<RoomRateResponseDTO> roomRates = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String requestBody = "{ \"query\": \"query minimumnightlyrates { listRoomRateRoomTypeMappings(where: {room_type_id: {equals: " + roomTypeId + "}, room_rate: {date: {gt: \\\"" + startDate + "\\\", lt: \\\"" + endDate + "\\\"}}}) { room_rate_id room_rate { date basic_nightly_rate } } }\" }";
            System.out.println(requestBody);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

            ResponseEntity<String> responseEntity = restTemplate.exchange(graphqlEndpoint, HttpMethod.POST, requestEntity, String.class);
            String responseBody = responseEntity.getBody();

            JsonNode responseJson = objectMapper.readTree(responseBody);
            JsonNode ratesNode = responseJson.get("data").get("listRoomRateRoomTypeMappings");
            if (ratesNode != null && ratesNode.isArray()) {
                Map<String, Double> minRates = new HashMap<>();
                for (JsonNode rateNode : ratesNode) {
                    String date = rateNode.get("room_rate").get("date").asText();
                    double basicNightlyRate = rateNode.get("room_rate").get("basic_nightly_rate").asDouble();

                    if (minRates.containsKey(date)) {
                        double currentMinRate = minRates.get(date);
                        if (basicNightlyRate < currentMinRate) {
                            minRates.put(date, basicNightlyRate);
                        }
                    } else {
                        minRates.put(date, basicNightlyRate);
                    }
                }
                for (Map.Entry<String, Double> entry : minRates.entrySet()) {
                    RoomRateResponseDTO roomRateDTO = new RoomRateResponseDTO(entry.getValue(), entry.getKey());
                    System.out.println(entry.getValue());
                    roomRates.add(roomRateDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(roomRates, new Comparator<RoomRateResponseDTO>() {
            @Override
            public int compare(RoomRateResponseDTO o1, RoomRateResponseDTO o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        System.out.println(roomRates);
        return roomRates;
    }
}
