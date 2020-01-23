package pl.lg.charity.validation.validators;

import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.validation.constraints.IsTheSamePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsTheSamePasswordForUserRegistration implements ConstraintValidator<IsTheSamePassword, RegistrationDataDTO> {


    @Override
    public void initialize(IsTheSamePassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(RegistrationDataDTO dataDTO, ConstraintValidatorContext context) {

        if (dataDTO.getPassword() == null || dataDTO.getRepassword() == null) {
            return true;
        }

        boolean valid = dataDTO.getPassword().equals(dataDTO.getRepassword());
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Podane hasła są różne !")
                    .addPropertyNode("repassword")
                    .addConstraintViolation();
        }
        return valid;
    }
}
