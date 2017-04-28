package models;

import be.objectify.deadbolt.core.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;
import play.data.validation.Validation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Interax on 04/06/2015.
 */
@Entity
public class SecurityRole extends BaseEntity implements Role {

    @Id
    private long id;

    @Column
    @Constraints.Required
    private String name;

    @ManyToMany
    @JsonIgnore
    List<SecurityPermission> permissions = new ArrayList<>();

    public SecurityRole() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecurityPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SecurityPermission> permissions) {
        this.permissions = permissions;
    }

    public static Finder<Long, SecurityRole> find = new Finder<Long, SecurityRole>(Long.class, SecurityRole.class);


}

