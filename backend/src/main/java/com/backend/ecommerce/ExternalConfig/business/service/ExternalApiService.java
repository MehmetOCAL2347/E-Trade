package com.backend.ecommerce.ExternalConfig.business.service;

import com.backend.ecommerce.ExternalConfig.business.requests.DistrictRequest;
import com.backend.ecommerce.ExternalConfig.business.requests.ProvinceRequest;
import com.backend.ecommerce.ExternalConfig.business.responses.DistrictResponse;
import com.backend.ecommerce.ExternalConfig.business.responses.ProvinceResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface ExternalApiService {
    Mono<DistrictResponse> getDistrictsFromProvince(DistrictRequest districtRequest);
    Mono<ProvinceResponse> getAllProvinces(ProvinceRequest provinceRequest);
}
