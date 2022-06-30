package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {

    @NotNull(message = "Region key mustn't be empty")
    @Size( min = 5 ,max = 50 , message = "Region key must be between 5 and 50")
    private String key;

    @NotNull(message = "Region nameUz mustn't be empty")
    @Size( min = 5 ,max = 50 , message = "Region nameUz must be between 5 and 50")
    private String nameUz;

    @NotNull(message = "Region nameRu mustn't be empty")
    @Size( min = 5 ,max = 50 , message = "Region nameRu must be between 5 and 50")
    private String nameRu;

    @NotNull(message = "Region nameEn mustn't be empty")
    @Size( min = 5 ,max = 50 , message = "Region nameEn must be between 5 and 50")
    private String nameEn;

    private String name;

}
