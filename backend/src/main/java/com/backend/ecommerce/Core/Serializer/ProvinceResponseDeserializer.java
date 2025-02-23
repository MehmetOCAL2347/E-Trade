package com.backend.ecommerce.Core.Serializer;

import com.backend.ecommerce.ExternalConfig.business.responses.ProvinceResponse;
import com.backend.ecommerce.ExternalConfig.entities.entity.Province;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceResponseDeserializer extends JsonDeserializer<ProvinceResponse> {

    private final String statusValue = "status";
    private final String dataValue = "data";
    private final String idValue = "id";
    private final String nameValue = "name";

    @Override
    public ProvinceResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode root = mapper.readTree(p);

        String status = root.get(statusValue).asText();

        List<Province> provinces = new ArrayList<>();

        JsonNode dataNode = root.get(dataValue);

        if (dataNode != null && dataNode.isArray()) {
            for (JsonNode node : dataNode) {
                Province province = new Province();
                province.setId(node.get("id").asInt());
                province.setName(node.get("name").asText());
                provinces.add(province);
            }
        }

        return ProvinceResponse.builder()
                .status(status)
                .data(provinces)
                .build();
    }
}
