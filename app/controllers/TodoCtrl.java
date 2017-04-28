package controllers;

import be.objectify.deadbolt.java.actions.Dynamic;
import com.fasterxml.jackson.databind.JsonNode;
import play.data.Form;
import play.mvc.*;
import utils.CtrlUtils;

import static play.libs.Json.toJson;


public class TodoCtrl extends Controller {

    public static final String Module = "todo";

    @Dynamic(value = Module)
    public static Result getAll(String order, Integer limit, Integer page) {
        return CtrlUtils.get(models.Todo
                    .find
                    .query()
                    .where()
                    .eq("user", SecurityCtrl.getUser())
                .query(), order, limit, page);
    }

    @Dynamic(value = "todo")
    public static Result createTodo() {
        Form<models.Todo> form = Form.form(models.Todo.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        else {
            models.Todo todo = form.get();
            todo.user = SecurityCtrl.getUser();
            todo.save();
            return ok(toJson(todo));
        }
    }

    @Dynamic(value = "todo")
    public static Result findById(Long id){
        models.Todo todo = models.Todo.find.byId(id);
        if(todo == null){
            return notFound();
        }else{
            return ok(toJson(todo));
        }
    }

    @Dynamic(value = "todo")
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateTodo(Long id){
        models.Todo todo = models.Todo.find.byId(id);
        if(todo != null){
            JsonNode node = request().body().asJson();
            todo.value = node.findPath("value").textValue();
            todo.done = node.findPath("done").booleanValue();
            todo.save();
            return ok(toJson(todo));
        }else{
            return notFound();
        }
    }

    @Dynamic(value = "todo")
    public static Result deleteTodo(Long id){
        models.Todo todo = models.Todo.find.byId(id);
        if(todo != null){
            todo.delete();
            return ok();
        }else{
            return notFound();
        }
    }
    
}
