package controllers;

import be.objectify.deadbolt.java.actions.Dynamic;
import com.avaje.ebean.Ebean;
import models.User;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.CtrlUtils;
import utils.FormUtils;

import static play.libs.Json.toJson;

/**
 * Created by HeavyPollo on 9/28/15.
 */
public class UserCtrl extends Controller{
    public static final String Module = "user";

    @Dynamic(value = Module)
    public static Result getAll(String order, Integer limit, Integer page) {
        return CtrlUtils.get(User.find.query(),order,limit,page);
    }

    @Dynamic(value = Module)
    public static Result get(long id){
        User user;
        user = User.find.byId(id);
        if(user == null ){
            return notFound();
        }
        return ok(toJson(user));
    }

    @Dynamic(value = Module)
    public static Result put(long id){
        Form<User> form = Form.form(User.class)
                .bindFromRequest();
        if (form.hasErrors()) {
            return CtrlUtils.responseWithErrors(form, Module);
        }else {

            Ebean.beginTransaction();
            try{
                User dto = form.get();
                CtrlUtils.checkUnique(User.find,"email",dto);
                dto.update();
                Ebean.commitTransaction();
            }catch (Exception e){
                Ebean.rollbackTransaction();
                if(e instanceof CtrlUtils.FormException){
                    return CtrlUtils.responseWithErrors((CtrlUtils.FormException)e,Module);
                }else{
                    Logger.error("", e);
                    return internalServerError();
                }

            }
            return ok();
        }
    }

    @Dynamic(value = Module)
    public static Result post(){
        Form<User> form = Form.form(User.class)
                .bindFromRequest();
        if (form.hasErrors()) {
//
//            Json.fromJson(Http.Context.current().request().body().asJson().get("role"), SecurityRole.class);
            return CtrlUtils.responseWithErrors(form,Module);
        }else {

            Ebean.beginTransaction();
            try{
                User dto = form.get();
                CtrlUtils.checkUnique(User.find,"email",dto);
                dto.save();
                Ebean.commitTransaction();
            }catch (Exception e){
                Ebean.rollbackTransaction();
                if(e instanceof CtrlUtils.FormException){
                    return CtrlUtils.responseWithErrors((CtrlUtils.FormException)e,Module);
                }else{
                    Logger.error("", e);
                    return internalServerError();
                }

            }
            return ok();
        }
    }


}
