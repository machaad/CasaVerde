package controllers;

import be.objectify.deadbolt.java.actions.Dynamic;
import models.SecurityRole;
import play.mvc.Result;

import static play.libs.Json.toJson;

/**
 * Created by HeavyPollo on 9/28/15.
 */
public class SecurityPermissionCtrl extends SecurityRoleCtrl {


    @Dynamic(value = "securityRole")
    public static Result getAll() {
        return ok(toJson(SecurityRole.find.all()));
    }
}
