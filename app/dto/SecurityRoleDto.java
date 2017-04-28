package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.SecurityPermission;
import models.SecurityRole;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeavyPollo on 9/28/15.
 */
public class SecurityRoleDto {
    private long id;

    @Constraints.Required
    private String name;

    private List<SecurityPermissionDto> permissions = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecurityPermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SecurityPermissionDto> permissions) {
        this.permissions = permissions;
    }

    public static class SecurityPermissionDto{

        private long id;

        private String value;

        private boolean right;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isRight() {
            return right;
        }

        public void setRight(boolean right) {
            this.right = right;
        }
    }

    public static SecurityRoleDto convert(SecurityRole role){
        SecurityRoleDto dto = new SecurityRoleDto();

        List<SecurityPermission> all = SecurityPermission.find.order("value").findList();

        dto.setId(role.getId());
        dto.setName(role.getName());

        all.forEach(permission -> {
            SecurityPermissionDto permissionDto = new SecurityPermissionDto();
            permissionDto.setId(permission.getId());
            permissionDto.setValue(permission.getValue());
            permissionDto.setRight(role.getPermissions().contains(permission));
            dto.getPermissions().add(permissionDto);
        });
        return dto;
    }

    public static SecurityRole convert(SecurityRoleDto dto){
        SecurityRole role;
        if(dto.getId() > 0){
            role = SecurityRole.find.byId(dto.getId());
        }else{
            role = new SecurityRole();
        }

        dto.setId(role.getId());
        dto.setName(role.getName());
        role.getPermissions().clear();
        dto.getPermissions().forEach(permission -> {
            if(permission.isRight()){
                role.getPermissions().add(SecurityPermission.find.byId(permission.getId()));
            }
        });
        return role;
    }
}
