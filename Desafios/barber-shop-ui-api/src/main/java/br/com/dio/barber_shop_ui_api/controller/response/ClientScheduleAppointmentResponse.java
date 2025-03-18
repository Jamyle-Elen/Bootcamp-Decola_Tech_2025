package br.com.dio.barber_shop_ui_api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record ClientScheduleAppointmentResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("day")
        Integer day,
        @JsonProperty("startAt")
        OffsetDateTime startAt,
        @JsonProperty("endAt")
        OffsetDateTime endAt,
        @JsonProperty("clientId")
        String clientId,
        @JsonProperty("clientName")
        String clientName
) {}
