package controllers;

import be.objectify.deadbolt.java.actions.Dynamic;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.SecurityRoleDto;
import models.SecurityPermission;
import models.SecurityRole;
import models.Todo;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.CtrlUtils;
import utils.FormUtils;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by HeavyPollo on 9/28/15.
 */
public class SecurityRoleCtrl extends Controller{
    public static final String Module = "securityRole";
    @Dynamic(value = Module)
    public static Result getAll(String order, Integer limit, Integer page) {
        return CtrlUtils.get(SecurityRole.find.query(), order, limit, page);
    }

    @Dynamic(value = Module)
    public static Result get(long id){
        SecurityRole role;
        if(id <= 0){
            role = new SecurityRole();
        }else{
            role = SecurityRole.find.byId(id);
        }
//        List<SecurityPermission> all = SecurityPermission.find.order("value").findList();
//        ObjectNode roleNode = JsonNodeFactory.instance.objectNode();
//        if(id <= 0){
//            roleNode.put("id",role.getId());
//        }
//        roleNode.put("name", role.getName());
//        ArrayNode permissionArray = JsonNodeFactory.instance.arrayNode();
//        all.forEach(permission -> {
//            ObjectNode permissionNode = JsonNodeFactory.instance.objectNode();
//            permissionNode.put("value", permission.getValue());
//            permissionNode.put("right", role.getPermissions().contains(permission));
//            permissionArray.add(permissionNode);
//        });
//        roleNode.put("permissions",permissionArray);
        return ok(toJson(SecurityRoleDto.convert(role)));
    }

    @Dynamic(value = Module)
    public static Result put(long id){
        Form<SecurityRole> form = Form.form(SecurityRole.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }else {
            SecurityRole role = form.get();
            role.save();
            return ok(toJson(role));
        }
    }

    @Dynamic(value = Module)
    public static Result post(){
        Form<SecurityRoleDto> form = Form.form(SecurityRoleDto.class).bindFromRequest();
        if (form.hasErrors()) {
            return CtrlUtils.responseWithErrors(form,Module);
        }else {
            SecurityRoleDto dto = form.get();
            Ebean.beginTransaction();
            SecurityRole role = new SecurityRole();
            role.setName(dto.getName());
            dto.getPermissions().forEach(p -> {
                if (p.isRight()) {
                    role.getPermissions().add(SecurityPermission.find.byId(p.getId()));
                }

            });
            try{
                role.save();
                Ebean.commitTransaction();
            }catch (Exception e){
                Logger.error("",e);
                Ebean.rollbackTransaction();
                return internalServerError();
            }
            return ok();
        }
    }


}
