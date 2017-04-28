package utils.containt;

import models.User;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.F;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by HeavyPollo on 10/1/15.
 */
public class UpperCaseValidator extends Constraints.Validator implements ConstraintValidator<UpperCase, Object> {

    final static public String message = "error.unique";
    @Override
    public boolean isValid(Object o) {
        if(o != null && StringUtils.isNotBlank(o.toString())){
            return o.toString().toUpperCase().equals(o.toString());
        }
        return true;
    }

    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return null;
    }

    @Override
    public void initialize(UpperCase constraintAnnotation) {
        Logger.warn(constraintAnnotation.toString());
    }
}
