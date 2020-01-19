package pl.lg.charity.validation.validators;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.dtos.DeleteAdminValidationDataDTO;
import pl.lg.charity.validation.constraints.ActiveAdmin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ActiveAdminForDeleteAdminDTO implements ConstraintValidator<ActiveAdmin, DeleteAdminValidationDataDTO> {

    @Override
    public void initialize(ActiveAdmin constraintAnnotation) {

    }

    @Override
    public boolean isValid(DeleteAdminValidationDataDTO dataDTO, ConstraintValidatorContext context) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(dataDTO, User.class);
        boolean valid = user.getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName());
        if (valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("ActiveAdmin.admin.*")
                    .addConstraintViolation();
        }
        return !valid;
    }
}
