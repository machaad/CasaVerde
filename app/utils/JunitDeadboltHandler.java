package utils;

/**
 * Created by Pmendoza on 01/07/2015.
 */

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import models.User;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by Interax on 05/06/2015.
 * WARINING solo se debe utilizar en pruebas unitarias
 */
public class JunitDeadboltHandler extends AbstractDeadboltHandler implements DynamicResourceHandler {
    private static final String USER = "user";
    @Override
    public F.Promise<Result> beforeAuthCheck(Http.Context context) {
        return F.Promise.pure(null);
    }

    @Override
    public Subject getSubject(Http.Context context) {
        if(context.args.get(USER) != null){
            return (Subject)context.args.get(USER);
        }
        User user = User.find.byId(1l);
        if (user != null) {
            context.args.put(USER, user);
            return user;
        }

        return null;
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
        return true;
    }

    @Override
    public boolean checkPermission(String s, DeadboltHandler deadboltHandler, Http.Context context) {
        return false;
    }
}
