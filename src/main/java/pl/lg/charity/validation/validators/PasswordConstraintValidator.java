package pl.lg.charity.validation.validators;

import org.passay.*;
import pl.lg.charity.enums.PolishCharacterData;
import pl.lg.charity.validation.constraints.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
           new LengthRule(8, 30),
           new CharacterRule(PolishCharacterData.UpperCase, 1),
           new CharacterRule(PolishCharacterData.LowerCase, 1),
           new CharacterRule(PolishCharacterData.Digit, 1),
           new CharacterRule(PolishCharacterData.Special, 1),
           new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(", ", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
