package models;

import be.objectify.deadbolt.core.models.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import models.User;
import play.db.ebean.Model;
import play.mvc.Http;
import utils.SecurityDeadboltHandler;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pmendoza on 23/06/2015.
 */
@MappedSuperclass
public class BaseEntity extends Model {

    public BaseEntity(){

    }

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "created=" + created +
                ", updated=" + updated +
                "} " + super.toString();
    }

    @PrePersist
    public void prePersist(){
        created = new Date();
        updated = new Date();
        try {
            User user = (User) Http.Context.current().args.get(SecurityDeadboltHandler.USER);
            if (user != null) {
                updatedBy = user;
                createdBy = user;
            }
        }catch (Exception e){}
    }

    @PreUpdate
    public void preUpdate(){
        updated = new Date();
        try {
            User user = (User) Http.Context.current().args.get(SecurityDeadboltHandler.USER);
            if (user != null) {
                updatedBy = user;
            }
        }catch (Exception e){}
    }
}
