package utils;

import com.avaje.ebean.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.ErrorDto;
import org.apache.commons.lang3.StringUtils;
import play.data.Form;
import play.data.validation.ValidationError;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static play.libs.Json.toJson;

/**
 * Created by HeavyPollo on 10/8/15.
 */
public class CtrlUtils {

    public static Result get(Query<?> query, String order, Integer limit, Integer page) {
        return get(query,order,limit,page,null);
    }

    /**
     *
     * @param query Finder.query() of the entity that will be working on
     * @param order field name for order, is this parametar has a dash as prefix it would mean that should in desc order
     * @param limit Number of rows per page
     * @param page Page
     * @param search Map that include the query paramters, the implementation take of entry as 'or like' expresion
     * @return
     */
    public static Result get(Query<?> query, String order, Integer limit, Integer page, Map<String,String> search) {
        if(StringUtils.isNotBlank(order)){
            query.order(fixOrder(order));
        }
        if(search != null){
            Junction<?> junction = query.where().disjunction();
            search.forEach((k,v) -> {
                junction.ilike("k",v + "%");
            });
        }
        if(limit <= 0 || page <= 0){
            List<?> all = query.findList();
            ObjectNode obj = Json.newObject();
            obj.put("data", toJson(all));
            obj.put("count", all.size());
            return Controller.ok(toJson(obj));
        }else {
            PagingList<?> pagingList = query.findPagingList(limit);
            pagingList.getFutureRowCount();
            ObjectNode obj = Json.newObject();
            obj.put("data", toJson(
                    pagingList.setFetchAhead(true).getPage(page - 1).getList()
            ));
            obj.put("count", pagingList.getTotalRowCount());
            return Controller.ok(toJson(obj));
        }
    }

    private static String fixOrder(String order){
        String fixed;
        if(order.startsWith("-")){
            fixed = order.substring(1,order.length()) + " desc";
        }else{
            fixed = order;
        }
        return fixed;
    }

    public static final Result responseWithErrors(Form<?> dtoForm,String module){
        List<ErrorDto> errors = new ArrayList<>();
        if(dtoForm.hasErrors()){
            for(Map.Entry<String, List<ValidationError>> entry : dtoForm.errors().entrySet()){
                for(ValidationError vError : entry.getValue()){
                    errors.add(new ErrorDto(entry.getKey(),vError.message(),module));
                }
            }
        }
        return Results.badRequest(Json.toJson(new Error(errors)));
    }

    public  static class Error{
        private List<ErrorDto> errors = new ArrayList<>();

        public Error(List<ErrorDto> errors) {
            this.errors = errors;
        }

        public List<ErrorDto> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorDto> errors) {
            this.errors = errors;
        }
    }

    public static final Result responseWithErrors(FormException fe,String module){
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(new ErrorDto(fe.field,fe.message,module));
        return Results.badRequest(Json.toJson(new Error(errors)));
    }

    public static final Result responseWithErrors(String field, String message,String module){
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(new ErrorDto(field,message,module));
        return Results.badRequest(Json.toJson(new Error(errors)));
    }

    public static final boolean checkUnique(Model.Finder finder, String fieldName,Object object) throws FormException {
        ExpressionList exp = null;
        try {
            if(object != null){
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
                exp = finder.where().eq(fieldName, pd.getReadMethod().invoke(object));
                Field idField = null;
                for(Field field : object.getClass().getDeclaredFields()){
                    if(field.isAnnotationPresent(javax.persistence.Id.class)){
                        idField = field;
                        break;
                    }
                }
                pd = new PropertyDescriptor(idField.getName(), object.getClass());
                exp.ne(idField.getName(),pd.getReadMethod().invoke(object));

            }
        } catch (Exception e) {
            play.Logger.warn("",e);
        }
        Object temp = exp.findUnique();
        if(temp != null){
            throw new FormException(fieldName, "notUnique");
        }
        return true;
    }
    public static class FormException extends Exception{
        private String field;
        private String message;

        /**
         *
         * @param field Field name. Example: email
         * @param message Message key. Example: error.require
         */
        public FormException(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}
