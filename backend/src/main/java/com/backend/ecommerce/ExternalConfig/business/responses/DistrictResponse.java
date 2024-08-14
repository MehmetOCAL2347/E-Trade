package com.backend.ecommerce.ExternalConfig.business.responses;

import com.backend.ecommerce.Core.Serializer.DistrictResponseDeserializer;
import com.backend.ecommerce.ExternalConfig.entities.entity.District;
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
@JsonDeserialize(using = DistrictResponseDeserializer.class)
public class DistrictResponse {
    private String status;
    private List<District> districts;
}
