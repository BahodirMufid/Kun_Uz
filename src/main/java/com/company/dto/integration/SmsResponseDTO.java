package com.company.dto.integration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// PROJECT NAME Kun_Uz
// TIME 18:11
// MONTH 06
// DAY 22
@Getter
@Setter
@ToString
public class SmsResponseDTO {
    private  Boolean success;
    private String reason;
    private Integer resultCode;

}
