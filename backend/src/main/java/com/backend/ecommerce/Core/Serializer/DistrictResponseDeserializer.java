package com.backend.ecommerce.Core.Serializer;

import com.backend.ecommerce.ExternalConfig.business.responses.DistrictResponse;
import com.backend.ecommerce.ExternalConfig.entities.entity.District;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistrictResponseDeserializer extends JsonDeserializer<DistrictResponse> {

    private final String statusValue = "status";
    private final String dataValue = "data";
    private final String districtsValue = "districts";
    private final String idValue = "id";
    private final String nameValue = "name";

    @Override
    public DistrictResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode root = mapper.readTree(p);

        String status = root.get(statusValue).asText();
        List<District> districts = new ArrayList<>();

        JsonNode dataNode = root.get(dataValue);

        if (dataNode.isArray()) {
            for (JsonNode node : dataNode) {
                JsonNode districtsNode = node.get(districtsValue);

                if (districtsNode != null && districtsNode.isArray()) {
                    for (JsonNode districtNode : districtsNode) {
                        District district = new District();
                        district.setId(districtNode.get(idValue).asInt());
                        district.setName(districtNode.get(nameValue).asText());
                        districts.add(district);
                    }
                }
            }
        }

        return DistrictResponse.builder()
                .status(status)
                .districts(districts)
                .build();
    }
}
