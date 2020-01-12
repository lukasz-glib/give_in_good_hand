package pl.lg.charity.dtos;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationDataDTO {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String lastName;
    @NotBlank @Email
    private String email;
    private String password;
    private String repassword;
    private Boolean active;
}
