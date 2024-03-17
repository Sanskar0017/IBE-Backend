package com.team14.IBE.Controller;

import com.team14.IBE.Service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TenantConfigController {
    private final TenantService tenantConfigService;

    @Autowired
    public TenantConfigController(TenantService tenantConfigService) {
        this.tenantConfigService = tenantConfigService;
    }

    @GetMapping("/config")
    public ResponseEntity<Map<String, Object>> getTenantConfig() throws IOException {
        Map<String, Object> configData = tenantConfigService.getTenantConfigData();
        return new ResponseEntity<>(configData, HttpStatus.OK);
    }
}



