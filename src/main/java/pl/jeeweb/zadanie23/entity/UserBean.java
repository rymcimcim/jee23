package pl.jeeweb.zadanie23.entity;

import java.io.Serializable;

public class UserBean implements Serializable {
    
    private String username;
    private int id;
    private String email;
    private String privilege;
    private String description;
    
    public UserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        this.description = "username=" + username + ",id=" + id + ",email=" + email + ",privilege=" + privilege;
    }
    
    
}
