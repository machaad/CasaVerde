package controllers;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.actions.Dynamic;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.SecurityPermission;
import models.User;
import play.api.libs.json.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.SecurityDeadboltHandler;

import java.util.*;

import static play.libs.Json.toJson;

/**
 * Created by HeavyPollo on 9/25/15.
 */
public class AppCtrl extends Controller {

    @SubjectPresent
    public static Result getMenu() {
        User user = (User) Http.Context.current().args.get(SecurityDeadboltHandler.USER);
        if(user == null || user.getPermissions() == null){
            return ok(toJson(Collections.emptyList()));
        }else{
            HashMap<String,List<Permission>> mp = new LinkedHashMap();

            List<SecurityPermission> perms = (List<SecurityPermission>)user.getPermissions();
            for(SecurityPermission perm : perms){
                List<Permission> p = mp.get(perm.getSection());
                if(p == null){
                    p = new ArrayList<>();
                }
                p.add(perm);
                mp.put(perm.getSection(),p);

            }
            ObjectNode userNode = JsonNodeFactory.instance.objectNode();
            userNode.put("name",user.getName());
            userNode.put("lastName",user.getLastName());
            userNode.put("email",user.getEmail());

            ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();

            for(Map.Entry<String,List<Permission>> entry : mp.entrySet()){
                ObjectNode node = JsonNodeFactory.instance.objectNode();
                node.put("section",entry.getKey());
                node.put("options",toJson(entry.getValue()));
                arrayNode.add(node);
            }
            ObjectNode main = JsonNodeFactory.instance.objectNode();
            main.put("user",userNode);
            main.put("menu",arrayNode);
            return ok(main);
        }
    }

}
