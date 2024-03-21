package com.team14.ibe.tests;

import com.team14.ibe.controller.RoomPageController;
import com.team14.ibe.dto.response.PromotionResponseDTO;
import com.team14.ibe.dto.response.RoomResponseDTO;
import com.team14.ibe.service.RoomPageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoomPageControllerTest {

    @Mock
    private RoomPageService roomPageService;

    @InjectMocks
    private RoomPageController roomPageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRoomTypes() {
        // Mocking service response
        List<RoomResponseDTO> roomTypes = new ArrayList<>();
        // Add mocked RoomResponseDTO objects as needed
        when(roomPageService.getAllRoomTypes(1,3)).thenReturn(roomTypes);

        // Call the controller method
        ResponseEntity<List<RoomResponseDTO>> response = roomPageController.getAllRoomTypes(1,3);

        // Verify that the service method is called once
        verify(roomPageService, times(1)).getAllRoomTypes(1,3);

        // Verify the response status code
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Add more assertions as needed
    }

    @Test
    public void testGetAllPromotions() {
        // Mocking service response
        List<PromotionResponseDTO> promotions = new ArrayList<>();
        // Add mocked PromotionResponseDTO objects as needed
        when(roomPageService.getAllPromotions(1,3)).thenReturn(promotions);

        // Call the controller method
        ResponseEntity<List<PromotionResponseDTO>> response = roomPageController.getAllPromotions(1,3);

        // Verify that the service method is called once
        verify(roomPageService, times(1)).getAllPromotions(1,3);

        // Verify the response status code
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Add more assertions as needed
    }
}
