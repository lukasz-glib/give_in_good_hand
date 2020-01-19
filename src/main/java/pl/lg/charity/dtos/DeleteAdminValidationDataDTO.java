package pl.lg.charity.dtos;

import lombok.Data;
import pl.lg.charity.validation.constraints.ActiveAdmin;

@Data
@ActiveAdmin
public class DeleteAdminValidationDataDTO {

    private Long id;
    private String email;
}
