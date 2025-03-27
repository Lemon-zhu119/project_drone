package com.ruoyi.api.service;

import java.math.BigDecimal;
import java.util.Map;

public interface GeocodingService {


    Map<String, Double> getCoordinates(String institutionAddress);
}
