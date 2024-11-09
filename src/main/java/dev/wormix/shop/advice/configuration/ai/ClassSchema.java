package dev.wormix.shop.advice.configuration.ai;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClassSchema(
    @JsonProperty(required = true, value = "class") String clazz
) {
}
