package utils;

import models.*;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

public class StartupData {

    public static User user1;
    public static User user2;
    public static Todo todo1_1;
    public static Todo todo1_2;
    public static Todo todo2_1;

    public static void load() {
        
        Logger.info("Loading starupData");
        List<SecurityPermission> permissionList = new ArrayList<>();

        SecurityPermission permission = new SecurityPermission();
        permission.setValue("securityRole");
        permission.setSection("management");
        permission.setIcon("assignment_ind");
        permission.save();
        permissionList.add(permission);

        permission = new SecurityPermission();
        permission.setValue("todo");
        permission.setSection("management");
        permission.setIcon("list");
        permission.save();
        permissionList.add(permission);

        permission = new SecurityPermission();
        permission.setValue("user");
        permission.setSection("management");
        permission.setIcon("people");
        permission.save();
        permissionList.add(permission);


        SecurityRole securityRole = new SecurityRole();
        securityRole.setName("admin");
        securityRole.setPermissions(permissionList);
        securityRole.save();

        user1 = new User("user1@demo.com", "password", "John","Doe");
        user1.setRole(securityRole);
        user1.save();
        
        todo1_1 = new Todo(user1, "make it secure");
        todo1_1.save();

        todo1_2 = new Todo(user1, "make it neat");
        todo1_2.save();

        user2 = new User("user2@demo.com", "password", "Jane","Doe");
        user2.save();

        todo2_1 = new Todo(user2, "make it pretty");
        todo2_1.save();

    }

}
