package pl.lg.charity.dtos;


import lombok.Data;
import pl.lg.charity.validation.constraints.ValidPassword;

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
    @ValidPassword
    private String password;
    @ValidPassword
    private String repassword;
    private Boolean active;
}
