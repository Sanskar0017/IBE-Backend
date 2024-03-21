package com.team14.IBE.dto;

import lombok.Data;

@Data
public class TenantConfigDTO {
    private String headerLogoImageUrl;
    private String pageTitleText;
    private String bannerImageUrl;
    private int maxStayDays;
    private String[] guestTypes;
    private int maxGuestCount;
    private Integer[] roomCountOptions;
    private int maxRooms;
}
