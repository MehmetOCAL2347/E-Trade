package com.backend.ecommerce.ExternalConfig.business.manager;

import com.backend.ecommerce.ExternalConfig.business.requests.DistrictRequest;
import com.backend.ecommerce.ExternalConfig.business.requests.ProvinceRequest;
import com.backend.ecommerce.ExternalConfig.business.responses.DistrictResponse;
import com.backend.ecommerce.ExternalConfig.business.responses.ProvinceResponse;
import com.backend.ecommerce.ExternalConfig.business.service.ExternalApiService;
import com.backend.ecommerce.ExternalConfig.entities.entity.Province;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExternalApiManager implements ExternalApiService {

    private WebClient webClient;
    private WebClient.Builder webClientBuilder;

    @Autowired
    public ExternalApiManager(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<DistrictResponse> getDistrictsFromProvince(DistrictRequest districtRequest) {

        this.webClient = webClientBuilder
                .baseUrl(districtRequest.getBaseUrl())
                .build();

        return this.webClient.get()
                .uri(districtRequest.getEndPoint())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        DistrictResponse districtResponse = objectMapper.readValue(response, DistrictResponse.class);
                        return districtResponse;
                    } catch (JsonProcessingException e) {
                        return DistrictResponse.builder().districts(Collections.emptyList()).build();
                    }
                });
    }

    @Override
    public Mono<ProvinceResponse> getAllProvinces(ProvinceRequest provinceRequest) {

        this.webClient = webClientBuilder
                .baseUrl(provinceRequest.getBaseUrl())
                .build();

        return this.webClient.get()
                .uri(provinceRequest.getEndPoint())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        ProvinceResponse provinceResponseResponse = objectMapper.readValue(response, ProvinceResponse.class);
                        return provinceResponseResponse;
                    } catch (JsonProcessingException e) {
                        return ProvinceResponse.builder().data(Collections.emptyList()).build();
                    }
                });
    }
}
