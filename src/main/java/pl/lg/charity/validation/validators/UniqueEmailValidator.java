package pl.lg.charity.validation.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.lg.charity.services.ValidationService;
import pl.lg.charity.validation.constraints.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Scope("prototype")
@Slf4j
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private ValidationService validationService;

    @Autowired
    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        log.debug("Walidacja adresu email: {}", email);
        Boolean unique = validationService.isUniqueEmail(email);
        log.debug("Czy adres email: {} jest {}", email, unique);
        if (unique) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Użytkownik o podanym adresie email już istnieje!")
                    .addConstraintViolation();
        }
        return !unique;
    }
}
