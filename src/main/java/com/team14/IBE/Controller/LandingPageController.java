package com.team14.IBE.Controller;

import com.team14.IBE.Service.LandingPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LandingPageController {
    @Autowired
    private LandingPageService landingPageService;
    @GetMapping("/property-rates")
    public Map<String, Double> getPropertyRates() {
        return landingPageService.getMinimumPricesByDate();
    }

}
