package br.com.dio.barber_shop_ui_api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record SaveScheduleResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("startAt")
        OffsetDateTime startAt,
        @JsonProperty("endAt")
        OffsetDateTime endAt,
        @JsonProperty("clientId")
        String clientId
) {}
