package utils.containt;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import javax.validation.*;


/**
 * Created by HeavyPollo on 10/1/15.
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UpperCaseValidator.class)
@play.data.Form.Display(name="constraint.upperCase")
public @interface UpperCase {
    String message() default UpperCaseValidator.message;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
