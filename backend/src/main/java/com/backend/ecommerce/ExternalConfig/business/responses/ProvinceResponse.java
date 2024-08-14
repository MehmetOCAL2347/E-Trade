package com.backend.ecommerce.ExternalConfig.business.responses;

import com.backend.ecommerce.Core.Serializer.ProvinceResponseDeserializer;
import com.backend.ecommerce.ExternalConfig.entities.entity.Province;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = ProvinceResponseDeserializer.class)
public class ProvinceResponse {
    private String status;
    private List<Province> data;
}
