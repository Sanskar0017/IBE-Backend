package com.team14.ibe.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team14.ibe.dto.Request.CreateBookingRequestDTO;
import com.team14.ibe.dto.response.CreateBookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookingMutationService {

    @Value("${graphql.endpoint}")
    private String graphqlEndpoint;

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper for JSON processing

    public CreateBookingResponse createBooking(CreateBookingRequestDTO requestDTO) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);

        // Construct the GraphQL mutation query
        String graphqlQuery = "{ \"query\": \"mutation MyMutation { createBooking(data: { check_in_date: \\\"" + requestDTO.getCheckInDate() + "\\\", check_out_date: \\\"" +
                requestDTO.getCheckOutDate() + "\\\", adult_count: " + requestDTO.getAdultCount() + ", child_count: " + requestDTO.getChildCount() + ", total_cost: " +
                requestDTO.getTotalCost() + ", amount_due_at_resort: " + requestDTO.getAmountDueAtResort() + ", guest: { create: { guest_name: \\\"" + requestDTO.getGuestName() +
                "\\\" } }, booking_status: { connect: { status_id: " + requestDTO.getStatusId() + " } }, property_booked: { connect: { property_id: " +
                requestDTO.getPropertyId() + " } }, promotion_applied: { create: { price_factor: " + requestDTO.getPriceFactor() + ", promotion_title: \\\"" +
                requestDTO.getPromotionTitle() + "\\\", promotion_description: \\\"" + requestDTO.getPromotionDescription() + "\\\" } } }) { booking_id check_in_date check_out_date adult_count child_count total_cost amount_due_at_resort guest_id property_id status_id promotion_id } }\"}";

        HttpEntity<String> entity = new HttpEntity<>(graphqlQuery, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                graphqlEndpoint,
                HttpMethod.POST,
                entity,
                String.class
        );
        System.out.println("response entity is: " + responseEntity);

        try {
            CreateBookingResponse response = objectMapper.readValue(responseEntity.getBody(), CreateBookingResponse.class);
            // call this method to update the availability
            //            Map<String, Object> updateResult = updateRoomAvailability(requestDTO.getAvailabilityId(), response.getBookingId());
            System.out.println(response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Map<String, Object> updateRoomAvailability(Long availabilityId, Long bookingId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);

        // Construct the GraphQL mutation query
        String graphqlQuery = "{ \"query\": \"mutation MyMutation { updateRoomAvailability(where: {availability_id: " + availabilityId +
                "}, data: {booking: {connect: {booking_id: " + bookingId + "}}}) { availability_id booking_id date } }\"}";

        HttpEntity<String> entity = new HttpEntity<>(graphqlQuery, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                graphqlEndpoint,
                HttpMethod.POST,
                entity,
                String.class
        );

        try {
            // Parse the JSON response and extract availability_id, booking_id, and date
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseJson = objectMapper.readTree(responseEntity.getBody());
            JsonNode dataNode = responseJson.get("data").get("updateRoomAvailability");

            Long updatedAvailabilityId = dataNode.get("availability_id").asLong();
            Long updatedBookingId = dataNode.get("booking_id").asLong();
            String updatedDate = dataNode.get("date").asText();

            // Construct and return the response map
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("availability_id", updatedAvailabilityId);
            responseMap.put("booking_id", updatedBookingId);
            responseMap.put("date", updatedDate);

            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
