package utils;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import controllers.SecurityCtrl;
import models.User;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

/**
 * <a href="https://github.com/schaloner/deadbolt-2">DeadBold</a> easy implementation
 * Created by Pedro Mendoza on 05/06/2015.
 */
public class SecurityDeadboltHandler extends AbstractDeadboltHandler implements DynamicResourceHandler {

    public static final String USER = "user";
    @Override
    public F.Promise<Result> beforeAuthCheck(Http.Context context) {
        if(getAuthorizationToken(context) != null){
            return F.Promise.pure(null);
        }else{
            return F.Promise.pure((Result)unauthorized());
        }
    }

    @Override
    public User getSubject(Http.Context context) {
        if(context.args.get(USER) != null){
            return (User)context.args.get(USER);
        }
        String authTokenHeaderValue = getAuthorizationToken(context);
        if (authTokenHeaderValue != null) {
            User user = models.User.findByAuthToken(authTokenHeaderValue);
            if (user != null) {
                context.args.put(USER, user);
                return user;
            }
        }
        return null;
    }

    public static User getCurrentSubject(){
        if(Http.Context.current().args.get(USER) != null){
            return (User)Http.Context.current().args.get(USER);
        }else{
            return null;
        }
    }

    @Override
    public F.Promise<Result> onAuthFailure(Http.Context context, String content) {
        return F.Promise.pure((Result)unauthorized());
    }

    @Override
    public DynamicResourceHandler getDynamicResourceHandler(Http.Context context) {
        return this;
    }

    @Override
    public boolean isAllowed(String name, String meta, DeadboltHandler deadboltHandler, Http.Context context) {
        Subject subject = deadboltHandler.getSubject(context);
        if(subject == null){
            return false;
        }
        for(Permission permission : subject.getPermissions()){
            if(permission.getValue().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkPermission(String s, DeadboltHandler deadboltHandler, Http.Context context) {
        return false;
    }

    private String getAuthorizationToken(Http.Context context) {
        String[] authTokenHeaderValues = context.request().headers().get(SecurityCtrl.AUTH_AUTHORIZATION.toUpperCase());
        if (authTokenHeaderValues == null) {
            authTokenHeaderValues = context.request().headers().get(SecurityCtrl.AUTH_AUTHORIZATION);
        }
        if (authTokenHeaderValues == null) {
            return null;
        }
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)){
            return authTokenHeaderValues[0];
        }else{
            return null;
        }
    }

}
