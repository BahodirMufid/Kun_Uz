package com.company.dto;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;

    @NotNull(message = "Mazgi Profile Name xato (is null)")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @NotNull(message = "Mazgi Profile Surname xato (is null)")
    @Size(min = 6, max = 255, message = "Surname must be between 3 and 255 characters")
    private String surname;

    @Email(message = "Email must be valid")
    @NotNull(message = "Mazgi Profile email xato (is null)")
    private String email;

    private ProfileRole role;
    private ProfileStatus status;

    @NotNull(message = "Mazgi Profile password xato (is null)")
    @Size(min = 8 , max = 20 ,message = "Password must be between 8 and 20 characters")
    private String password;
    private String photoId;

    private String jwt;

}
