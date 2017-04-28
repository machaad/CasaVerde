package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Todo extends BaseEntity {

    @Id
    public Long id;

    @Column(length = 140, nullable = false)
    @Constraints.MaxLength(140)
    @Constraints.Required
    public String value;

    @ManyToOne
    @JsonIgnore
    public User user;

    public boolean done = false;

    public Todo(User user, String value) {
        this.user = user;
        this.value = value;
    }

    public static List<Todo> findByUser(User user) {
        return find.where().eq("user", user).findList();
    }
    public static final  Finder<Long, Todo> find = new Finder<Long, Todo>(Long.class, Todo.class);
}
