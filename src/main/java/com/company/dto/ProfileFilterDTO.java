package com.company.dto;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileFilterDTO {
    private Integer id;

    private String name;
    private String surname;
    private String email;

    private ProfileRole role;
    private ProfileStatus status;

    private String createdDateTo;
    private String createdDateFrom;


    //name,surname,phone,role,created_date_from,created_date_to


}
