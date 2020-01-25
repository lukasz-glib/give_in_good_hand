package pl.lg.charity.validation.constraints;

import pl.lg.charity.validation.validators.IsTheSamePasswordForUpdateUserData;
import pl.lg.charity.validation.validators.IsTheSamePasswordForUpdateUserDataByAdmin;
import pl.lg.charity.validation.validators.IsTheSamePasswordForUserRegistration;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {IsTheSamePasswordForUserRegistration.class, IsTheSamePasswordForUpdateUserData.class,
        IsTheSamePasswordForUpdateUserDataByAdmin.class})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsTheSamePassword {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
