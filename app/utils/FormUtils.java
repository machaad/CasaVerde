package utils;

import com.avaje.ebean.ExpressionList;
import dto.ErrorDto;
import models.User;
import play.data.Form;
import play.data.validation.ValidationError;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import javax.persistence.Id;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by HeavyPollo on 9/29/15.
 */
public class FormUtils {


}
