package pl.lg.charity.validation.validators;

import pl.lg.charity.dtos.UpdateUserDataDTO;
import pl.lg.charity.validation.constraints.IsTheSamePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsTheSamePasswordForUpdateUserData implements ConstraintValidator<IsTheSamePassword, UpdateUserDataDTO> {

    @Override
    public void initialize(IsTheSamePassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(UpdateUserDataDTO dataDTO, ConstraintValidatorContext context) {

        boolean valid = dataDTO.getPassword().equals(dataDTO.getRepassword());
        if (!valid || dataDTO.getPassword() == null || dataDTO.getRepassword() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Podane hasła są różne !")
                    .addPropertyNode("repassword")
                    .addConstraintViolation();
        }
        return valid;
    }
}
