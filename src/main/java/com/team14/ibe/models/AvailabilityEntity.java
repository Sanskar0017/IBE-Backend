package com.team14.ibe.models;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "availability")
public class AvailabilityEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availabilityId;
    private String bookingId;
}
