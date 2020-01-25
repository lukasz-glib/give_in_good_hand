package pl.lg.charity.dtos;

import lombok.Data;
import pl.lg.charity.validation.constraints.IsTheSamePassword;
import pl.lg.charity.validation.constraints.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data @IsTheSamePassword
public class UpdateUserDataByAdminDataDTO {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String lastName;
    @NotBlank
    @ValidPassword
    private String password;
    private String repassword;
    @NotBlank @Email
    private String email;
    private Boolean active;
}
