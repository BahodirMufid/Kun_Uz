package com.company.dto.integration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

// PROJECT NAME Kun_Uz
// TIME 17:02
// MONTH 06
// DAY 20

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmsRequestDTO {

    private String key;
    private String phone;
    private String message;

}
