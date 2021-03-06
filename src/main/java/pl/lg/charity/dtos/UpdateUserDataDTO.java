package pl.lg.charity.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserDataDTO {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String lastName;
    @NotBlank @Email
    private String email;
    private Boolean active;
}
