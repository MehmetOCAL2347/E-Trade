package com.backend.ecommerce.ExternalConfig.webApi;

import com.backend.ecommerce.ExternalConfig.business.requests.DistrictRequest;
import com.backend.ecommerce.ExternalConfig.business.requests.ProvinceRequest;
import com.backend.ecommerce.ExternalConfig.business.responses.DistrictResponse;
import com.backend.ecommerce.ExternalConfig.business.responses.ProvinceResponse;
import com.backend.ecommerce.ExternalConfig.business.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fetch-data")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    @PostMapping("/getDistrictsFromProvince")
    public Mono<DistrictResponse> getDistrictsFromProvince(@RequestBody DistrictRequest districtRequest){
        return externalApiService.getDistrictsFromProvince(districtRequest);
    }

    @PostMapping("/getAllProvinces")
    public Mono<ProvinceResponse> getAllProvinces(@RequestBody ProvinceRequest provinceRequest){
        return externalApiService.getAllProvinces(provinceRequest);
    }

}
