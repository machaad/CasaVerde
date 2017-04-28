package models;

import be.objectify.deadbolt.core.models.Permission;

import javax.persistence.*;

/**
 * Created by Interax on 04/06/2015.
 */
@Entity
public class SecurityPermission extends BaseEntity implements Permission{

    @Id
    private long id;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private String section;

    @Column
    private String icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
    public static Finder<Long, SecurityPermission> find = new Finder<Long, SecurityPermission>(Long.class, SecurityPermission.class);

}
