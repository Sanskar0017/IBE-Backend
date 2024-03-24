package com.team14.ibe.service;


import com.team14.ibe.modals.TenantConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class TenantService {
    public Map<String, Object> getTenantConfigData() throws IOException {
        TenantConfig tenantConfig = new TenantConfig();
        return tenantConfig.getConfigData();
    }
}
