package pl.lg.charity.dtos;

import lombok.Data;
import pl.lg.charity.validation.constraints.IsTheSamePassword;
import pl.lg.charity.validation.constraints.ValidPassword;

import javax.validation.constraints.NotBlank;

@Data @IsTheSamePassword
public class UpdateUserPasswordDataDTO {

    private String username;
    private String lastName;
    @NotBlank @ValidPassword
    private String password;
    private String repassword;
}
