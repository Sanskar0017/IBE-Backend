package com.team14.ibe.tests;

import com.team14.ibe.dto.response.PromotionResponseDTO;
import com.team14.ibe.dto.response.RoomResponseDTO;
import com.team14.ibe.service.RoomPageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoomPageServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RoomPageService roomPageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRoomTypes() {
        ResponseEntity<String> responseEntity = ResponseEntity.ok("{\"data\": {\"listRoomTypes\": []}}");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        List<RoomResponseDTO> roomTypes = roomPageService.getAllRoomTypes();

        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));

        assertEquals(new ArrayList<RoomResponseDTO>(), roomTypes);
    }

    @Test
    void testGetAllPromotions() {
        // Mocking restTemplate.exchange() response
        ResponseEntity<String> responseEntity = ResponseEntity.ok("{\"data\": {\"listPromotions\": []}}");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        // Call the service method
        List<PromotionResponseDTO> promotions = roomPageService.getAllPromotions();

        // Verify restTemplate.exchange() is called once
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));

        // Add assertions as needed
        assertEquals(new ArrayList<PromotionResponseDTO>(), promotions);
    }
}
