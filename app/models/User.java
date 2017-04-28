package models;

import be.objectify.deadbolt.core.models.*;
import be.objectify.deadbolt.core.models.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;
import utils.containt.UpperCase;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User extends BaseEntity implements Subject{

    @Id
    public Long id;

    @JsonIgnore
    private String authToken;
    
    @Column(length = 256, unique = true, nullable = false)
    @Constraints.Required
    private String email;


    @Column(length = 64, nullable = false)
    private byte[] shaPassword;

    @Transient
    @JsonIgnore
    private String password;

    @Column(length = 256, nullable = false)
    public String name;

    @Column(length = 256, nullable = false)
    public String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Todo> todos = new ArrayList<Todo>();

    @ManyToOne
    private SecurityRole role;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        shaPassword = getSha512(password);
    }

    public User(String emailAddress, String password, String name, String lasName) {
        setEmail(emailAddress);
        setPassword(password);
        this.name = name;
        this.lastName = lasName;
    }

    public String createToken() {
        authToken = UUID.randomUUID().toString();
        save();
        return authToken;
    }

    public void deleteAuthToken() {
        authToken = null;
        save();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public SecurityRole getRole() {
        return role;
    }

    public void setRole(SecurityRole role) {
        this.role = role;
    }

    @Override
    @JsonIgnore
    public List<? extends Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        if(roles != null) {
            roles.add(role);
        }
        return roles;
    }

    @Override
    @JsonIgnore
    public List<? extends Permission> getPermissions() {
        if(role == null){
            return new ArrayList<>();
        }else{

        }return role.getPermissions();

    }

    @Override
    @JsonIgnore
    public String getIdentifier() {
        return String.valueOf(id);
    }

    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    public static User findByAuthToken(String authToken) {
        if (authToken == null) {
            return null;
        }

        try  {
            return find.where().eq("authToken", authToken).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static User findByEmailAddressAndPassword(String emailAddress, String password) {
        // todo: verify this query is correct.  Does it need an "and" statement?
        return find.where().eq("email", emailAddress.toLowerCase()).eq("shaPassword", getSha512(password)).findUnique();
    }
}
