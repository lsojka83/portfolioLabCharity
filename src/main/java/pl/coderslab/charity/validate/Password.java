package pl.coderslab.charity.validate;

import pl.coderslab.charity.model.Messages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default  Messages.INVALID_PASSWORD;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
