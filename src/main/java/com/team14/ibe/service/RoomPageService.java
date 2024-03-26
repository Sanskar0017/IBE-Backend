package com.team14.ibe.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team14.ibe.dto.response.PromotionResponseDTO;
import com.team14.ibe.dto.response.RoomAvailabilityDTO;
import com.team14.ibe.dto.response.RoomRateDTO;
import com.team14.ibe.dto.response.RoomResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomPageService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${graphql.endpoint}")
    private String graphqlEndpoint;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<RoomResponseDTO> getAllRoomTypes(int page, int size, boolean singleBeds, boolean superDeluxe, boolean familyDeluxe) {
        int skip = (page - 1) * size;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);

        StringBuilder queryBuilder = new StringBuilder("{ \"query\": \"{ listRoomTypes(where: { property_id: { equals: 14 }");
        if (singleBeds) {
            queryBuilder.append(", single_bed: { gt: 0 }");
        }

        if (familyDeluxe || superDeluxe) {
            queryBuilder.append(", OR: [");
            if (familyDeluxe) {
                queryBuilder.append("{ room_type_name: { equals: \\\"FAMILY_DELUXE\\\" } }");
                if (superDeluxe) {
                    queryBuilder.append(",");
                }
            }
            if (superDeluxe) {
                queryBuilder.append("{ room_type_name: { equals: \\\"SUPER_DELUXE\\\" } }");
            }
            queryBuilder.append("]");
        }

        queryBuilder.append(" }, take: ").append(size).append(", skip: ").append(skip).append(") { room_type_id room_type_name area_in_square_feet single_bed double_bed max_capacity } }\\\" }\" }");
        HttpEntity<String> requestEntity = new HttpEntity<>(queryBuilder.toString(), httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(graphqlEndpoint, HttpMethod.POST, requestEntity, String.class);
        String responseBody = responseEntity.getBody();

        List<RoomResponseDTO> roomTypes = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode responseJson = objectMapper.readTree(responseBody);
            JsonNode roomTypesNode = responseJson.get("data").get("listRoomTypes");
            if (roomTypesNode != null) {
                for (JsonNode roomTypeNode : roomTypesNode) {
                    int roomTypeId = roomTypeNode.get("room_type_id").asInt();
                    String roomTypeName = roomTypeNode.get("room_type_name").asText();
                    int areaInSquareFeet = roomTypeNode.get("area_in_square_feet").asInt();
                    int singleBed = roomTypeNode.get("single_bed").asInt();
                    int doubleBed = roomTypeNode.get("double_bed").asInt();
                    int maxCapacity = roomTypeNode.get("max_capacity").asInt();
                    RoomResponseDTO roomResponseDTO = new RoomResponseDTO(roomTypeId, roomTypeName, areaInSquareFeet, singleBed, doubleBed, maxCapacity);
                    roomTypes.add(roomResponseDTO);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomTypes;
    }

    public List<PromotionResponseDTO> getAllPromotions(int page, int size) {
        int skip = (page - 1) * size;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);

        String requestBody = String.format("{ \"query\": \"{ listPromotions(where: {}, take: %d, skip: %d) { promotion_id promotion_title promotion_description minimum_days_of_stay is_deactivated price_factor } }\" }", size, skip);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(graphqlEndpoint, HttpMethod.POST, requestEntity, String.class);
        String responseBody = responseEntity.getBody();

        List<PromotionResponseDTO> promotions = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode responseJson = objectMapper.readTree(responseBody);
            JsonNode promotionsNode = responseJson.get("data").get("listPromotions");
            if (promotionsNode != null) {
                for (JsonNode promotionNode : promotionsNode) {
                    String promotionDescription = promotionNode.get("promotion_description").asText();
                    int promotionId = promotionNode.get("promotion_id").asInt();
                    String promotionTitle = promotionNode.get("promotion_title").asText();
                    boolean isDeactivated = promotionNode.get("is_deactivated").asBoolean();
                    int minimumDaysOfStay = promotionNode.get("minimum_days_of_stay").asInt();
                    double priceFactor = promotionNode.get("price_factor").asDouble();
                    PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(promotionDescription, promotionId, promotionTitle, isDeactivated, minimumDaysOfStay, priceFactor);
                    promotions.add(promotionResponseDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promotions;
    }

    public List<RoomAvailabilityDTO> getRoomAvailability() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);

        String requestBody = "{ \"query\": \"query RoomAvailabilityQuery { listRoomAvailabilities(where: {property_id: {equals: 14}, date: {gte: \\\"2024-03-01T00:00:00.000Z\\\", lte: \\\"2024-03-01T00:00:00.000Z\\\"}}) { date room { room_id room_type { room_type_name } } } }\" }";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(graphqlEndpoint, HttpMethod.POST, requestEntity, String.class);
        String responseBody = responseEntity.getBody();

        List<RoomAvailabilityDTO> roomAvailabilities = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode responseJson = objectMapper.readTree(responseBody);
            JsonNode availabilitiesNode = responseJson.get("data").get("listRoomAvailabilities");
            if (availabilitiesNode != null) {
                for (JsonNode availabilityNode : availabilitiesNode) {
                    String date = availabilityNode.get("date").asText();
                    JsonNode roomNode = availabilityNode.get("room");
                    int roomId = roomNode.get("room_id").asInt();
                    String roomTypeName = roomNode.get("room_type").get("room_type_name").asText();
                    RoomAvailabilityDTO roomAvailabilityDTO = new RoomAvailabilityDTO(date, roomId, roomTypeName);
                    roomAvailabilities.add(roomAvailabilityDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomAvailabilities;
    }

    public List<RoomRateDTO> getRoomRate() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("x-api-key", apiKey);

        String requestBody = "{ \"query\": \"query RoomRateQuery { listRoomRateRoomTypeMappings(where: {room_rate: {date: {gte: \\\"2024-03-01T00:00:00.000Z\\\", lte: \\\"2024-03-03T00:00:00.000Z\\\"}}, room_type: {property_id: {equals: 14}}}) { room_rate { basic_nightly_rate date } room_type { room_type_name } } }\" }";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(graphqlEndpoint, HttpMethod.POST, requestEntity, String.class);
        String responseBody = responseEntity.getBody();

        List<RoomRateDTO> roomRates = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode responseJson = objectMapper.readTree(responseBody);
            JsonNode ratesNode = responseJson.get("data").get("listRoomRateRoomTypeMappings");
            if (ratesNode != null) {
                for (JsonNode rateNode : ratesNode) {
                    double basicNightlyRate = rateNode.get("room_rate").get("basic_nightly_rate").asDouble();
                    String date = rateNode.get("room_rate").get("date").asText();
                    String roomTypeName = rateNode.get("room_type").get("room_type_name").asText();
                    RoomRateDTO roomRateDTO = new RoomRateDTO(basicNightlyRate, date, roomTypeName);
                    roomRates.add(roomRateDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomRates;
    }
}
